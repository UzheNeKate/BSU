import {createForm} from "../create.js"

const formConfig = [
  {
    tag: "input",
    name: "author",
    tagType: "text",
    reg: "^[а-яА-ЯёЁa-zA-Z]+ [а-яА-ЯёЁa-zA-Z]+ ?[а-яА-ЯёЁa-zA-Z]+$",
    style: {
      width: "300px",
      height: "24px",
      "font-size":"15px"
    },
  },
  {
    tag: "input",
    name: "title",
    tagType: "text",
    reg: "^[а-яА-ЯёЁa-zA-Z]+ [а-яА-ЯёЁa-zA-Z]+ ?[а-яА-ЯёЁa-zA-Z]+$",
    style: {
      width: "300px",
      height: "24px",
        "font-size":"15px"
    },
  },
  {
    tag: "button",
    tagType: "submit",
    tagText: "Поиск",
    style: {
      width: "100px",
      height: "30px",
        "font-size":"16px"
    },
  },
];

createForm("#number", formConfig)

