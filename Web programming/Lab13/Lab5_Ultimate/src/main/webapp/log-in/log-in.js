import { createForm } from "../create.js";
import { validate } from "../validate.js";

const formConfig = [
  {
    tag: "h3",
    tagText: "Войти",
    style: {
      "margin-bottom": "8px",
      "font-size": "22px",
    },
  },
  { tag: "p", tagText: "E-mail:" },

  {
    tag: "input",
    tagType: "email",
    name: "name",
    reg: `^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$`,
    style: {
      width: "300px",
      height: "24px",
      "font-size": "16px",
    },
  },
  {
    tag: "p",
    tagText: "Некоректный формат E-mail",
    style: {
      height: "16px",
      color: "red",
      opacity: 0,
      "font-size": "16px",
    },
  },
  { tag: "p", tagText: "Пароль:" },
  {
    tag: "input",
    tagType: "password",
    name: "password",
    reg: `^[a-zA-Z0-9]+$`,
    style: {
      width: "300px",
      height: "24px",
      "font-size": "16px",
    },
  },

  {
    tag: "p",
    tagText: "Только латинские буквы и цифры",
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
    tagText: "Войти",
    style: {
      width: "160px",
      height: "34px",
      "margin-top": "20px",
      "font-size": "18px",
    },
  },
];

createForm("#login", formConfig);
validate("login");
