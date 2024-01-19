import axios from "axios";
import BindingClass from "../util/bindingClass";
import Authenticator from "./authenticator";

export default class NineAmClient extends BindingClass {

    constructor(props = {}) {
        super();

              const methodsToBind = ['clientLoaded', 'getIdentity', 'login', 'logout', 'getQuestion','sendUserAnswer'];

        this.bindClassMethods(methodsToBind, this);

        this.authenticator = new Authenticator();;
        this.props = props;

        axios.defaults.baseURL = process.env.API_BASE_URL;
        this.axiosClient = axios;
        this.clientLoaded();
    }

    /**
     * Run any functions that are supposed to be called once the client has loaded successfully.
     */
    clientLoaded() {
        if (this.props.hasOwnProperty("onReady")) {
            this.props.onReady(this);
        }
    }

    /**
     * Get the identity of the current user
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The user information for the current user.
     */
    async getIdentity(errorCallback) {
        try {
            const isLoggedIn = await this.authenticator.isUserLoggedIn();

            if (!isLoggedIn) {
                return undefined;
            }

            return await this.authenticator.getCurrentUserInfo();
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async login() {
        this.authenticator.login();
    }

    async logout() {
        this.authenticator.logout();
    }

    async getTokenOrThrow(unauthenticatedErrorMessage) {
        const isLoggedIn = await this.authenticator.isUserLoggedIn();
        if (!isLoggedIn) {
            throw new Error(unauthenticatedErrorMessage);
        }

        return await this.authenticator.getUserToken();
    }

    async getQuestion(date, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can view contacts.");
            const response = await this.axiosClient.get(`/questions/daily/${date}`, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.question;
        } catch (error) {
            this.handleError(error, errorCallback);
        }
    }
    async deleteUserAnswer(questionId, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can delete answers.");
            const response = await this.axiosClient.delete(`questions/${questionId}`, {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            });

            console.log("Answer deleted successfully:", response.data);
            return response.data;
        } catch (error) {
            this.handleError(error, errorCallback);
            throw error;
        }
    }

    async getViewHistory(correctOnly, errorCallback) {
            try {
                    const token = await this.getTokenOrThrow("Only authenticated users can see all contacts.");
                    const response = await this.axiosClient.get(`answers?correctOnly=${correctOnly}`, {
                        headers: {
                            Authorization: `Bearer ${token}`
                        }
                    });
                    return response.data.userAnswers;
                } catch (error) {
                        this.handleError(error, errorCallback)
                }
            }

    async sendUserAnswer(selectedAnswer, date, errorCallback) {
           try {
               const token = await this.getTokenOrThrow("Only authenticated users can save answers.");
               const response = await this.axiosClient.post(
                   `/answers`,
                   {
                       userChoice: selectedAnswer,
                       date: date,
                   },
                   {
                       headers: {
                           Authorization: `Bearer ${token}`,
                       },
                   }
               );
               console.log("User answer saved successfully:", response.data);
               return response.data.answer;
           } catch (error) {
               this.handleError(error, errorCallback);
               throw error;
           }
       }
    /**
     * Helper method to log the error and run any error functions.
     * @param error The error received from the server.
     * @param errorCallback (Optional) A function to execute if the call fails.
     */
    handleError(error, errorCallback) {
        console.error(error);

        const errorFromApi = error?.response?.data?.error_message;
        if (errorFromApi) {
            console.error(errorFromApi)
            error.message = errorFromApi;
        }

        if (errorCallback) {
            errorCallback(error);
        }
    }
}