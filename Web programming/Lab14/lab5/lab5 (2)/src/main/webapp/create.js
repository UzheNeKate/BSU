export function createForm(name, config) {
  const form = document.querySelector(name);
  config.forEach((el) => {
    let style = "";
    if (el.style) {
      for (let key in el.style) {
        style += `${key}:${el.style[key]};`;
      }
    }
    form.innerHTML += `
      <${el.tag}
        ${el.tagType ? "type=" + el.tagType : "text"}
        ${el.reg ? "data-reg=" + el.reg : ""}
        ${el.name ? "name=" + el.name : ""}
        style='${style}'
      >
      ${el.tagText || ""}
      </${el.tag}>
    `;
  });
}