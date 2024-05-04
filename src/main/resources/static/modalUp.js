var updateModal = document.getElementById('updateModal')
var updateButton = updateModal.querySelector('.update-button');
//окно обновления
updateModal.addEventListener('show.bs.modal', function (event) {
console.log("Hello, I'm up!");
    var button = event.relatedTarget;
    var taskId = button.getAttribute('data-bs-whatever')

    console.log("получаю данные обновляемой задачи")
    fetch('/apitasks/' + taskId)
        .then(response => response.json()
            .then(function (json) {
                 updateModal.querySelector('.up-task-id').value = json.id
                 updateModal.querySelector('.up-task-title').value = json.title
                 updateModal.querySelector('.up-task-description').value = json.description
            }))
})

//кнопка удаления
updateButton.addEventListener('click', function (){

var taskId =  updateModal.querySelector('.up-task-id').value

let task = {
        id: updateModal.querySelector('.up-task-id').value,
        title: updateModal.querySelector('.up-task-title').value,
        description: updateModal.querySelector('.up-task-description').value
    };

//    if (confirm('Are you sure you want to delete this task?')) {
        fetch('/apitasks/' + taskId, {
                method: 'PUT',
                  headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify(task)
            }).then(response => {
                          if (response.ok) {
//                              confirm('Пользователь успешно обновлен');
                              $('#updateModalCloseButton').click();
                              fillTasksTable();
                          } else {
                              confirm('Ошибка обновления');
                          }
                      })
//    }
})