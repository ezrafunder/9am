import NineAmClient from '../api/NineAmClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

class ViewQuestion extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'addQuestionToPage', 'submit',], this);
        this.dataStore = new DataStore();
        this.selectedAnswer = null;
        this.dataStore.addChangeListener(this.addQuestionToPage);
        this.header = new Header(this.dataStore);
        console.log("viewQuestion constructor");
    }

    async clientLoaded() {
        try {
            console.log('Sending API request to fetch question...');
            const question = await this.client.getQuestion("2023-11-17");
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

        document.getElementById('viewHistoryButton').addEventListener('click', this.viewHistory);

        this.clientLoaded();

    }

     viewHistory() {
            window.location.href = 'viewHistory.html';
        }


    async submit(evt) {
        evt.preventDefault();

        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = ``;
        errorMessageDisplay.classList.add('hidden');


        //add error message in html

        const submitButton = document.getElementById('submit');
        const origButtonText = submitButton.innerText;
       // submitButton.innerText = 'Loading...';

        //  const answerElement = document.querySelector('input[name="answer"]:checked');
        if (document.getElementById('A').checked) {
            this.selectedAnswer = document.getElementById('A').value;
        }
        else if (document.getElementById('B').checked) {
            this.selectedAnswer = document.getElementById('B').value;
        }
        else if (document.getElementById('C').checked) {
            this.selectedAnswer = document.getElementById('C').value;
        }
        else if (document.getElementById('D').checked) {
            this.selectedAnswer = document.getElementById('D').value;
        }
        const answer = await this.client.sendUserAnswer(this.selectedAnswer, this.dataStore.get('question').date, (error) => {
            submitButton.innerText = origButtonText;
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        });
        //console.log('Answer sent:', answer);
            document.getElementById('result').innerText = `Your Answer: ${this.selectedAnswer} ${answer.correct}`; //reformat
        //  List<UserAnswer> userAnswers = getUserAnswersFromDynamoDB(userId);
        //  ViewHistoryResult result = new ViewHistoryResult(userAnswers);
    
    }

    addQuestionToPage() {
        const fetchedQuestion = this.dataStore.get('question');

        if (fetchedQuestion) {
            const questionElement = document.getElementById('question');
            questionElement.innerText = fetchedQuestion.question;

            const answerElement = document.getElementById('answer');
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
            }
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