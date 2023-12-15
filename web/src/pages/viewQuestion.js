import NineAmClient from '../api/NineAmClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

class ViewQuestion extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'addQuestionToPage'], this);
        this.dataStore = new DataStore();
        this.selectedAnswer = null;
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
        document.getElementById('submit').addEventListener('click', this.submit);

        this.clientLoaded();

    }

    async submit(evt) {
            evt.preventDefault();

            const errorMessageDisplay = document.getElementById('error-message');
            errorMessageDisplay.innerText = ``;
            errorMessageDisplay.classList.add('hidden');

            const createButton = document.getElementById('create');
            const origButtonText = createButton.innerText;
            createButton.innerText = 'Loading...';

            const groupName = document.getElementById('group-name').value;
            const tagsText = document.getElementById('tags').value;

            let tags;
            if (tagsText.length < 1) {
                tags = null;
            } else {
                tags = tagsText.split(/\s*,\s*/);
            }

            const group = await this.client.createGroup(groupName, tags, (error) => {
                createButton.innerText = origButtonText;
                errorMessageDisplay.innerText = `Error: ${error.message}`;
                errorMessageDisplay.classList.remove('hidden');
            });
            this.dataStore.set('group', group);
        }


 addQuestionToPage() {
        const fetchedQuestion = this.dataStore.get('question');

        if (fetchedQuestion) {
            const questionElement = document.getElementById('question');
            questionElement.innerText = fetchedQuestion.question;

            const answerElement = document.getElementById('answer');

            // Create radio buttons for answer choices
            for (const letter in fetchedQuestion.answerChoices) {
                const choiceElement = document.createElement('input');
                choiceElement.type = 'radio';
                choiceElement.name = 'answer';
                choiceElement.id = letter;
                choiceElement.value = fetchedQuestion.answerChoices[letter];
                answerElement.appendChild(choiceElement);

                const labelElement = document.createElement('label');
                labelElement.innerText = fetchedQuestion.answerChoices[letter];
                labelElement.setAttribute('for', letter);
                answerElement.appendChild(labelElement);

                //google how to check selected radio button
            }

            // Add listener to submit button
            const submitButton = document.getElementById('submitButton');
            submitButton.addEventListener('click', () => {
                if (this.selectedAnswer) {
                    this.client.sendUserAnswer(fetchedQuestion.questionId, this.selectedAnswer);
                } else {
                    alert('Please select an answer choice!');
                }
            });
        } else {
            const questionElement = document.getElementById('question');
            questionElement.innerText = "Question not found";
        }
    }
}


//{
//      "question": {
//          "date": "2023-11-19",
//          "answer": "Paris",
//          "questionId": "55",
//          "answerChoices": {
//              "A": "Paris",
//              "B": "London",
//              "C": "Berlin",
//              "D": "Rome"
//          },
//          "question": "What is the capital of France?"
//      }
//  }


/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const viewQuestion = new ViewQuestion();
    viewQuestion.mount();
};

window.addEventListener('DOMContentLoaded', main);