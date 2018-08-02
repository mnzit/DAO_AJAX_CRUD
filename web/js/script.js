var id, name, address, phoneno, email;
$(document).ready(function () {
    $('#addUser').submit(function (event) {
        event.preventDefault();
        $.ajax({
            url: 'UserController',
            type: 'POST',
            dataType: 'json',
            data: $('#addUser').serialize(),
            success: function (val) {
                $('#userTable tbody').append('<tr id="user-' + val.id + '"><td>' + val.id + '</td><td>' + val.name + '</td><td>' + val.address + '</td><td>' + val.phoneno + '</td><td>' + val.email + '</td><td><button type="button" class="btn btn-danger" data-toggle="modal" data-target="#exampleModal">Update</button><button type="button" class="btn btn-danger">Delete</button></td></tr>');
            }
        });
    });
    $('#updateUser').submit(function (event) {
        event.preventDefault();
        $.ajax({
            url: 'UserController',
            type: 'POST',
            dataType: 'json',
            data: $('#updateUser').serialize(),
            success: function (val) {
//                $('#userTable tbody').append('<tr id="user-' + val.id + '"><td>' + val.id + '</td><td>' + val.name + '</td><td>' + val.address + '</td><td>' + val.phoneno + '</td><td>' + val.email + '</td><td><button type="button" class="btn btn-danger" data-toggle="modal" data-target="#exampleModal">Update</button><button type="button" class="btn btn-danger">Delete</button></td></tr>');
            
            }
        });
    });
});

function getByUserId(id) {
    $.ajax({
        url: 'UserUpdateController',
        type: 'GET',
        dataType: 'json',
        data: {"userId": id},
        success: function (value) {
            id = value[0].userId;
            name = value[0].name;
            address = value[0].address;
            phoneno = value[0].phoneNo;
            email = value[0].email;
            $('#modal-id').val(value[0].userId);
            $('#modal-name').val(value[0].name);
            $('#modal-address').val(value[0].address);
            $('#modal-phoneno').val(value[0].phoneNo);
            $('#modal-email').val(value[0].email);
        }
    });
}