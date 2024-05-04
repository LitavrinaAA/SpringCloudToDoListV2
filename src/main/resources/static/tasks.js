var allTasksTable = $("#allTasksTable"); // Получить элемент таблицы по идентификатору

document.addEventListener('DOMContentLoaded', async function () {
    try {
        await fillTasksTable(); // Заполнить таблицу данными задач
        console.log('задачи загружены!');
    } catch (error) {
        console.error('There has been a problem:', error);
    }
});


function fillTasksTable() {
  allTasksTable.empty(); // Удалить все существующие карточки

  fetch('/apitasks') // Отправить запрос GET к API
    .then(res => res.json()) // Преобразовать ответ в JSON
    .then(data => {
      data.forEach(task => { // Перебрать массив задач
      console.log(task.id, task.name, task.description );

//    Заполнить карточку
        let card =
          `<div class="col-md-4">
            <div class="card">
              <div class="card-header">
                <h5 class="card-title">${task.title}</h5>
              </div>
              <div class="card-body">
                <p class="card-text">${task.description}</p>

              </div>
              <div class="modal-footer">
              <button type="button"
                                  class="btn btn-danger"
                                  data-bs-toggle="modal"
                                  data-bs-target="#delModal"
                                  data-bs-whatever="${task.id}">
                                      Удалить
                              </button>
                              <button type="button"
                                   class="btn btn-primary"
                                   data-bs-toggle="modal"
                                   data-bs-target="#updateModal"
                                   data-bs-whatever="${task.id}">
                                      Обновить
                              </button>
              </div>
            </div>
          </div>`
        ;

        allTasksTable.append(card); // Добавить карточку в контейнер
      });
    });
}
