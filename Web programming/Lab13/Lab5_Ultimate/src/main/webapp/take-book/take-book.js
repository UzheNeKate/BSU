import { createForm } from "../create.js";
import { validate } from "../validate.js";

const formConfig = [
  {
    tag: "h3",
    tagText: "Взять книгу",
    style: {
      "margin-bottom": "8px",
      "font-size": "22px",
    },
  },
  { tag: "p", tagText: "Автор:" },

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
    tagText: "Некорректный формат ФИО",
    style: {
      height: "16px",
      color: "red",
      opacity: 0,
      "font-size": "16px",
    },
  },
  { tag: "p", tagText: "Название книги:" },
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
    tagText: "Запрещено использовать спецсимволы",
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
    tagText: "Взять",
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
