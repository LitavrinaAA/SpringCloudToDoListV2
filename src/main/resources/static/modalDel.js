
var delModal = document.getElementById('delModal');
var delButton = delModal.querySelector('.del-button');

//окно удаления
delModal.addEventListener('show.bs.modal', function (event) {
    console.log("Hello, I'm del!");
    var button = event.relatedTarget;
    var taskId = button.getAttribute('data-bs-whatever')
    console.log(taskId);
    var modalTitle = delModal.querySelector('.del-modal-title')
    modalTitle.textContent = 'Удалить задачу с ID: ' + taskId

     fetch('/apitasks/' + taskId)
            .then(response => response.json()
                .then(function (json) {
                     delModal.querySelector('.del-task-id').value = json.id
                     console.log(".del-task-id : " + json.id)
                     delModal.querySelector('.del-task-title').value = json.title
                     delModal.querySelector('.del-task-description').value = json.description
                }))


})

//кнопка удаления
delButton.addEventListener('click', function (){
var taskId =  delModal.querySelector('.del-task-id').value
    console.log("Hello, I'm del-button! ID: " + taskId);

//    if (confirm('Are you sure you want to delete this task?')) {
        fetch('/apitasks/' + taskId, {
                method: 'DELETE',
            }).then(response => {
                          if (response.ok) {
//                              confirm('Пользователь успешно удален');
                              $('#deleteModalCloseButton').click();
                              fillTasksTable();
                          } else {
                              confirm('Ошибка удаления');
                          }
                      })
//    }
    })

