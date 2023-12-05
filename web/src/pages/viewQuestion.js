import NineAmClient from '../api/NineAmClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

class ViewQuestion extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'addQuestionToPage'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.addQuestionToPage);
        this.header = new Header(this.dataStore);
        console.log("viewQuestion constructor");
    }

    async clientLoaded() {
        try {
            console.log('Sending API request to fetch question...');
            const question = await this.client.getQuestion("2023-11-19");
            console.log('Question data received:', question);
            this.dataStore.set('question', question);
        } catch (error) {
            console.error('Error loading question:', error);
            this.dataStore.set('question', null);
        }
    }


    mount() {
        this.header.addHeaderToPage();
        this.client = new NineAmClient();
        this.clientLoaded();
    }


 addQuestionToPage() {
        const fetchedQuestion = this.dataStore.get('question');


            if (fetchedQuestion) {
                const questionElement = document.getElementById('question');
                questionElement.innerText = fetchedQuestion.question;
            } else {
                const questionElement = document.getElementById('question');
                questionElement.innerText = "Question not found";
            }
        }
}


/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const viewQuestion = new ViewQuestion();
    viewQuestion.mount();
};

window.addEventListener('DOMContentLoaded', main);