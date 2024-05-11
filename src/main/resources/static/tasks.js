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
//<div class="col">
//          <div class="card shadow-sm">
//            <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"/><text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>
//
//            <div class="card-body">
//              <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
//              <div class="d-flex justify-content-between align-items-center">
//                <div class="btn-group">
//                  <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
//                  <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
//                </div>
//                <small class="text-muted">9 mins</small>
//              </div>
//            </div>
//          </div>
//        </div>
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
