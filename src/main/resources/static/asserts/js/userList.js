document.addEventListener('DOMContentLoaded', function() {
    fetch('users').then(response => response.json())
        .then(result => {
            if (result && result.success) {
                // 获取用户数据
                const users = result.data;
                let currentPage = 1;
                const usersPerPage = 5; // 每页显示的用户数量

                // 加载用户数据到用户管理表
                const userList = document.getElementById('userList');
                const pagination = document.getElementById('pagination');

                // 遍历用户数据并处理每个用户
                function loadUsers(page) {
                    userList.innerHTML = '';
                    const start = (page - 1) * usersPerPage;
                    const end = start + usersPerPage;
                    const paginatedUsers = users.slice(start, end);

                    paginatedUsers.forEach((user, index) => {
                        const row = document.createElement('tr');
                        row.innerHTML = `
                        <td>${user.id}</td>
                        <td>${user.name}</td>
                        <td>${user.age}</td>
                        <td>${user.gender === "1" ? "男" : "女"}</td>
                        <td>${user.email}</td>
                        <td>${user.role === "1" ? "管理员" : "普通用户"}</td>
                        <td>${user.active === "1" ? "在线" : "离线"}</td>
                        <td>${user.registerDate}</td>
                        <td class="text-end">
                        <button onclick="editUser(${user.id})" class="btn btn-primary">编辑</button>
                        <button onclick="deleteUser(${user.id})" class="btn btn-primary">删除</button>
                        </td>`;
                        userList.appendChild(row);
                    });
                    renderPagination()
                }

                //删除功能
                window.deleteUser = function(index) {
                    let flag = window.confirm("确认删除ID为" + index +"的用户？")
                    const userId = index;
                    console.log('删除用户的ID:', userId);

                    if(flag){
                        fetch(`delUser/${userId}`, {method: 'DELETE'}).then(response => response.json()).then(
                            result => {
                                if (result.success) {
                                    console.log(result.data);
                                    alert("删除成功")
                                } else {
                                    console.log("error");
                                    alert("删除失败")
                                }
                                location.reload();
                            }
                        ).catch(error => {
                            console.log(error);
                        })
                    }else {
                        location.reload();
                    }
                }

                //修改功能
                window.editUser = function(index) {
                    const userId = index;
                    console.log("修改的用户ID" + userId);
                    fetch(`editUser/${userId}`, {method: ''}).then(response => response.json()).then()
                }

                //渲染分页
                function renderPagination() {
                    pagination.innerHTML = '';
                    const totalPages = Math.ceil(users.length / usersPerPage);

                    const paginationList = document.createElement('ul');
                    paginationList.classList.add('pagination');

                    for (let i = 1; i <= totalPages; i++) {
                        const pageItem = document.createElement('li');
                        pageItem.classList.add('page-item');

                        const pageLink = document.createElement('button');
                        pageLink.classList.add('page-link');
                        pageLink.innerText = i;
                        pageLink.addEventListener('click', () => {
                            currentPage = i;
                            loadUsers(currentPage);
                        });

                        if (i === currentPage) {
                            pageItem.classList.add('active');
                        }

                        pageItem.appendChild(pageLink);
                        paginationList.appendChild(pageItem);
                    }

                    pagination.appendChild(paginationList);
                }

                loadUsers(currentPage);
            } else {
                console.log(result.message);
            }
        })
        .catch(error => {
            console.error('请求错误:', error);
        });

})

