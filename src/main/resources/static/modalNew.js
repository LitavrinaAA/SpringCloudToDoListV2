var newModal = document.getElementById('newModal')
var newButton = newModal.querySelector('.new-button');

newModal.addEventListener('show.bs.modal', function (event) {
    console.log("Hello, I'm new!");
})

//кнопка создания
newButton.addEventListener('click', function (){

var taskId =  updateModal.querySelector('.up-task-id').value

let task = {
        title: newModal.querySelector('.new-task-title').value,
        description: newModal.querySelector('.new-task-description').value
    };

//    if (confirm('Are you sure you want to delete this task?')) {
        fetch('/apitasks', {
                method: 'POST',
                  headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify(task)
            }).then(response => {
                          if (response.ok) {
//                              confirm('Пользователь успешно обновлен');
                              $('#newModalCloseButton').click();
                              fillTasksTable();
                          } else {
                              confirm('Ошибка создания');
                          }
                      })
//    }
})