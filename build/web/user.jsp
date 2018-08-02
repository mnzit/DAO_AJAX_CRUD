<%-- 
    Document   : index
    Created on : Jul 30, 2018, 1:57:59 PM
    Author     : Dell
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <title>Demo AJAX</title>
    </head>
    <body>
        <div class="container">
            <div class="jumbotron jumbotron-fluid">
                <h1 class="display-4 text-center">Users</h1>
            </div>
            <form id="addUser" autocomplete="off">
                <div class="form-row">
                    <div class="form-group col-md-3">
                        <label for="Name">Name</label>
                        <input type="text" class="form-control" id="name" placeholder="Eg: Jonathan Stager" name="name">
                    </div>
                    <div class="form-group col-md-3">
                        <label for="Address">Address</label>
                        <input type="text" class="form-control" id="address" placeholder="Eg: Melborne" name="address">
                    </div>
                    <div class="form-group col-md-3">
                        <label for="Phoneno">Phone no.</label>
                        <input type="number" class="form-control" id="phoneno" placeholder="Eg: 9800000000" name="phone_no">
                    </div>
                    <div class="form-group col-md-3">
                        <label for="Email">Email</label>
                        <input type="email" class="form-control" id="email" placeholder="Eg: jonathan_stager@mail.com" name="email">
                    </div>
                </div>
                <button type="submit" class="btn btn-primary">Add</button>
            </form>
            <table class="table mt-4 table-hover" id="userTable">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">User id</th>
                        <th scope="col">Name</th>            
                        <th scope="col">Address</th>
                        <th scope="col">Phone no.</th>
                        <th scope="col">Email</th>
                        <th scope="col">Operations</th>
                    </tr>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${userlist}" var="u">
                        <tr id="user-${u.userId}">
                            <td><c:out value="${u.userId}" /></td>
                            <td><c:out value="${u.name}" /></td>
                            <td><c:out value="${u.address}" /></td>
                            <td><c:out value="${u.phoneNo}" /></td>
                            <td><c:out value="${u.email}" /></td>

                            <td>
                                <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#exampleModal" onclick ="getByUserId('${u.userId}')">Update</button>
                                <button type="button" class="btn btn-danger" onclick ="delete('${u.userId}')">Delete</button>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Update User</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form id="updateUser" autocomplete="off">
                            <div class="form-row">
                                <div class="form-group col-md-2">
                                    <label for="userid">User Id</label>
                                    <input readonly="readonly"type="text" class="form-control" id="modal-id"  name="userId">
                                </div>
                                <div class="form-group col-md-10">
                                    <label for="Name">Name</label>
                                    <input type="text" class="form-control" id="modal-name" placeholder="Eg: Jonathan Stager" name="name">
                                </div>
                                <div class="form-group col-md-12">
                                    <label for="Address">Address</label>
                                    <input type="text" class="form-control" id="modal-address" placeholder="Eg: Melborne" name="address">
                                </div>
                                <div class="form-group col-md-12">
                                    <label for="Phoneno">Phone no.</label>
                                    <input type="number" class="form-control" id="modal-phoneno" placeholder="Eg: 9800000000" name="phone_no">
                                </div>
                                <div class="form-group col-md-12">
                                    <label for="Email">Email</label>
                                    <input type="email" class="form-control" id="modal-email" placeholder="Eg: jonathan_stager@mail.com" name="email">
                                </div>
                            </div>
                            <button type="submit" class="btn btn-primary">Update</button>
                    </div>
                    <div class="modal-footer">

                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/script.js"></script>
    <footer class="page-footer">
        <div class="footer-copyright">
            <div class="container">
                © 2018 Copyright by Manjit!
            </div>
        </div>
    </footer>
</html>

