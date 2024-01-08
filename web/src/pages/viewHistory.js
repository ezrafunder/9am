import NineAmClient from '../api/NineAmClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';
class ViewHistory extends BindingClass {
     constructor() {
           super();
           this.bindClassMethods(['clientLoaded', 'mount', 'viewHistory'], this);
           this.dataStore = new DataStore();
           this.header = new Header(this.dataStore);
           this.dataStore.addChangeListener(this.viewHistory);
           console.log("viewHistory constructor");
       }
        async clientLoaded() {
               try {
                   console.log('Sending API request to fetch question...');
                   const userAnswers = await this.client.getViewHistory();
                   console.log('Answers received:', userAnswers);
                   this.dataStore.set('userAnswers', userAnswers);
               } catch (error) {
                   console.error('Error loading user answers:', error);
                   this.dataStore.set('userAnswers', null);
               }
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
            row.insertCell().textContent = userAnswer.correct;
        }
    }
}

//add a checkbox column, when they are checked they can click a delete entries button located at the bottom


   const main = async () => {
       const viewHistory = new ViewHistory();
           viewHistory.mount();
   };
   window.addEventListener('DOMContentLoaded', main);