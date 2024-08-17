const btnGetAll = document.querySelector(".get-all button");

btnGetAll.addEventListener('click', async () => {
    const res = await fetch("/api/client/find/all");
    const data = await res.json();
    console.log(data);
    return data;
});
