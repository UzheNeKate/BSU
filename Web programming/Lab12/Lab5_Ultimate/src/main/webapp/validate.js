export function validate(name) {
  const form = document.forms[name];
  const formArr = Array.from(form);
  const validFormArr = [];
  const button = form.elements["button"];

  formArr.forEach((el) => {
    if (el.hasAttribute("data-reg")) {
      validFormArr.push(el);
    }
  });

  form.addEventListener("input", inputHandler);
  button.addEventListener("click", buttonHandler);

  function inputHandler({ target }) {
    if (target.hasAttribute("data-reg")) {
      inputCheck(target);
    }
  }

  function inputCheck(el) {
    const inputValue = el.value;
    const inputReg = el.getAttribute("data-reg");
    const reg = new RegExp(inputReg);
    if (reg.test(inputValue)) {
      el.style.border = "2px solid rgb(0, 196, 0)";
      el.nextSibling.nextSibling.style.opacity = 0;
      return 0;
    } else {
      el.style.border = "2px solid rgb(255, 0, 0)";
      el.nextSibling.nextSibling.style.opacity = 1;
      return 1;
    }
  }

  function buttonHandler() {
    let flag = 0;
    validFormArr.forEach((el) => {
      flag += inputCheck(el);
    });
  }
}
