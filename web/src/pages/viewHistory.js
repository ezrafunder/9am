import NineAmClient from '../api/NineAmClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

class ViewHistory extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'viewHistory', 'deleteUserAnswer', 'toggleCorrectOnly'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
        this.dataStore.addChangeListener(this.viewHistory);
        this.showCorrectOnly = false;
        console.log("viewHistory constructor");
        const toggleButton = document.getElementById("toggleCorrectOnlyButton");
            if (toggleButton) {
                toggleButton.addEventListener("click", () => this.toggleCorrectOnly());
                }
    }

    async clientLoaded() {
            try {
                console.log('Sending API request to fetch user answers...');
                const userAnswers = await this.client.getViewHistory(this.showCorrectOnly);
                console.log('User answers received:', userAnswers);
                this.dataStore.set('userAnswers', userAnswers);
            } catch (error) {
                console.error('Error loading user answers:', error);
                this.dataStore.set('userAnswers', null);
            }
        }
         toggleCorrectOnly() {
                this.showCorrectOnly = !this.showCorrectOnly;
                this.clientLoaded();
            }

    mount() {
        this.header.addHeaderToPage();
        this.client = new NineAmClient();
        this.clientLoaded();
    }

    viewHistory() {
        console.log("viewHistory");
        const userAnswers = this.dataStore.get('userAnswers');
        if (userAnswers == null) {
            console.log("userAnswers is null");
            return;
        }
        const tbody = document.getElementById("historyTableBody");
        tbody.innerHTML = "";

        for (const userAnswer of userAnswers) {
            console.log("userAnswer: ", userAnswer);
            const row = tbody.insertRow();
            row.insertCell().textContent = userAnswer.date;
            row.insertCell().textContent = userAnswer.question;
            row.insertCell().textContent = userAnswer.userChoice;
            row.insertCell().textContent = userAnswer.isCorrect === 'true' ? 'Yes' : 'No';


            const deleteButton = document.createElement("button");
            deleteButton.textContent = "Delete";
            deleteButton.addEventListener("click", () => this.deleteUserAnswer(userAnswer.questionId));

            row.insertCell().appendChild(deleteButton);
        }
    }

    async deleteUserAnswer(questionId) {
        try {
            await this.client.deleteUserAnswer(questionId);
            this.clientLoaded();
        } catch (error) {
            console.error("Error deleting answer:", error);
        }
    }
}

const main = async () => {
    const viewHistory = new ViewHistory();
    viewHistory.mount();
};

window.addEventListener('DOMContentLoaded', main);
