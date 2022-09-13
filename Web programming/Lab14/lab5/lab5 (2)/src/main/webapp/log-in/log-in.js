import {createForm} from "../create.js";
import {validate} from "../validate.js";

let en = {
    signIn: 'Sign in',
    incorrectEmail: 'Incorrect format of E-mail',
    password: 'Password:',
    onlyLatin: 'Only latin characters and digits',
};

let ru = {
    signIn: 'Войти',
    incorrectEmail: 'Некоректный формат E-mail',
    password: 'Пароль:',
    onlyLatin: 'Только латинские буквы и цифры',
};

function getLocale(lang) {
    return lang === 'ru' ? ru : en;
}

let locale = getLocale(lang);

const formConfig = [
    {
        tag: "h3",
        tagText: locale['signIn'],
        style: {
            "margin-bottom": "8px",
            "font-size": "22px",
        },
    },
    {tag: "p", tagText: "E-mail:"},

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
        tagText: locale['incorrectEmail'],
        style: {
            height: "16px",
            color: "red",
            opacity: 0,
            "font-size": "16px",
        },
    },
    {tag: "p", tagText: locale['password']},
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
        tagText: locale['onlyLatin'],
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
        tagText: locale['signIn'],
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
