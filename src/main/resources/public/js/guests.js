var api = "http://localhost:8080/api/guest/";
var table = $('#table').DataTable();
var items = [];

function getAll() {
    $.get(api, function(data) {

        if (data == null) throw "NoData";

        table.clear();

        for (i = 0; i < data.length; i++) {

            var item = data[i];

            var dateOfBirth = "";
            if (item.dateOfBirth != null) {
                dateOfBirth = item.dateOfBirth[2] + "-" + item.dateOfBirth[1] + "-" + item.dateOfBirth[0];
            }

            table.row.add([
                "<a href=\"javascript:update(" + item.id + ")\">" + item.lastName + "</a>",
                item.firstName,
                item.address,
                item.postalCode,
                item.city,
                item.email,
                item.phoneNumber,
                dateOfBirth
            ]);

        }

        items = data;

        table.draw();
    });
}


function add() {
	// Set title
    $("#title").text("New Guest");

    // Hide delete button
    $("#deleteBtn").hide();

    // Clear form
    clearForm();

    // Show form
    $("#form").modal('toggle');
}

function deleteItem() {
	 var id = $("#id").val();

	 var uri =  api + id + "/";

	 // Send data
    $.ajax({
        url: uri,
        type: "DELETE"
    }).then(function() {
        $("#form").modal('toggle');
        getAll();
    });
}

function update(id) {
	// Set title
    $("#title").text("Edit Guest");
    
    // Show delete button
    $("#deleteBtn").show();

    // Clear form
    clearForm();

    // Get item
    var item;
    for (var i = 0; i < items.length; i++) {
        if (items[i].id == id) {
            item = items[i];
            break;
        }
    };

    if (item == null) throw "NoItemFound";

    // Fill form
    $("#id").val(item.id);
    $("#firstName").val(item.firstName);
    $("#lastName").val(item.lastName);
    $("#address").val(item.lastName);
    $("#postalCode").val(item.postalCode);
    $("#city").val(item.city);
    $("#email").val(item.email);
    $("#phoneNumber").val(item.phoneNumber);

    // Date conversion
    if (item.dateOfBirth != null) {
    	var date = new Date(item.dateOfBirth[0] + "-" + item.dateOfBirth[1] + "-" + item.dateOfBirth[2]);
        $("#dateOfBirth").val(date.toISOString().substring(0, 10));
    }

    // Show form
    $("#form").modal('toggle');
}

function save(e) {
    e.preventDefault();

    // Create obj
    var obj = {};

    obj.id = $("#id").val();
    obj.firstName = $("#firstName").val();
    obj.lastName = $("#lastName").val();
    obj.address = $("#address").val();
    obj.postalCode = $("#postalCode").val();
    obj.city = $("#city").val();
    obj.email = $("#email").val();
    obj.phoneNumber = $("#phoneNumber").val();
    obj.dateOfBirth = $("#dateOfBirth").val();

    var uri = api;
    var method = "POST";

    // Is this an update?
    if (id == null) {
        uri = api + obj.id + "/";
        method = "PUT"
    }

    // Send data
    $.ajax({
        url: uri,
        type: method,
        data: JSON.stringify(obj),
        contentType: "application/json; charset=utf-8"
    }).then(function() {
        $("#form").modal('toggle');
        getAll();
    });
}

function clearForm() {
    $("input").each(function() {
        $(this).val("");
    });
}

$("#addBtn").click(add);
$("#saveBtn").click(save);
$("#deleteBtn").click(deleteItem);
getAll();