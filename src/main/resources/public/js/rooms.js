var api = "http://localhost:8080/api/room/";
var table = $('#table').DataTable();
var items = [];

function getAll() {
    $.get(api, function(data) {

        if (data == null) throw "NoData";

        table.clear();

        for (i = 0; i < data.length; i++) {

            var item = data[i];

            table.row.add([
                "<a href=\"javascript:update(" + item.id + ")\">" + item.id + "</a>",
                item.roomType
            ]);

        }

        items = data;

        table.draw();
    });
}


function add() {
	// Set title
    $("#title").text("New Room");

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
    $("#title").text("Edit Room");

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
    $("#roomType").val(item.roomType);

    // Show form
    $("#form").modal('toggle');
}

function save(e) {
    e.preventDefault();

    // Create obj
    var obj = {};

    obj.id = $("#id").val();
    obj.roomType = $("#roomType").val();

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