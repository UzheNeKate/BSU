import { createForm } from "../create.js";
import { validate } from "../validate.js";

let en = {
  takeBook: 'Take book',
  author: 'Author:',
  incorrect_fio: 'Incorrect format fio',
  title: 'Title:',
  specSymbols: 'Cannot use special symbols',
  take: 'Take'
};

let ru = {
  takeBook: 'Взять книгу',
  author: 'Автор:',
  incorrect_fio: 'Некорректный формат ФИО',
  title: '"Название книги:',
  specSymbols: 'Запрещено использовать спецсимволы',
  take: 'Взять'
};

function getLocale(lang) {
  return lang === 'ru' ? ru : en;
}

let locale = getLocale(lang);

const formConfig = [
  {
    tag: "h3",
    tagText: locale['takeBook'],
    style: {
      "margin-bottom": "8px",
      "font-size": "22px",
    },
  },
  { tag: "p", tagText: locale['author'] },

  {
    tag: "input",
    name: "author",
    tagType: "text",
    reg: `^[а-яА-ЯёЁa-zA-Z]+ [а-яА-ЯёЁa-zA-Z]+ ?[а-яА-ЯёЁa-zA-Z]+$`,
    style: {
      width: "300px",
      height: "24px",
      "font-size": "16px",
    },
  },

  {
    tag: "p",
    tagText: locale['incorrect_fio'],
    style: {
      height: "16px",
      color: "red",
      opacity: 0,
      "font-size": "16px",
    },
  },
  { tag: "p", tagText: locale['title'] },
  {
    tag: "input",
    name: "title",
    tagType: "text",
    reg: `^[а-яА-ЯёЁa-zA-Z0-9\\s]+$`,
    style: {
      width: "300px",
      height: "24px",
      "font-size": "16px",
    },
  },

  {
    tag: "p",
    tagText: locale['specSymbols'],
    style: {
      height: "16px",
      color: "red",
      opacity: 0,
      "font-size": "16px",
    },
  },
  {
    tag: "button",
    tagType: "submit",
    tagText: locale['take'],
    style: {
      width: "70px",
      height: "34px",
      "margin-top": "8px",
      "font-size": "18px"
    }
  }
];

createForm("#take", formConfig);
validate("take");
