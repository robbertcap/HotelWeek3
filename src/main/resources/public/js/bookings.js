var api = "http://localhost:8080/api/booking/";
var table = $('#table').DataTable();
var items = [];

function getAll() {
    $.get(api, function(data) {

        if (data == null) throw "NoData";

        table.clear();

        for (i = 0; i < data.length; i++) {

            var item = data[i];

            var start = "";
            if (item.start != null) {
                start = item.start[2] + "-" + item.start[1] + "-" + item.start[0];
            }

            var end = "";
            if (item.end != null) {
                end = item.end[2] + "-" + item.end[1] + "-" + item.end[0];
            }


            table.row.add([
                "<a href=\"javascript:update(" + item.id + ")\">" + item.id + "</a>",
                item.guest.lastName,
                item.room.id,
                start,
                end,
                item.comments
            ]);

        }

        items = data;

        table.draw();
    });
}


function add() {
    // Set title
    $("#title").text("New Booking");

    // Hide delete button
    $("#deleteBtn").hide();

    // Clear form
    clearForm();

    // Show form
    $("#form").modal('toggle');
}

function deleteItem() {
    var id = $("#id").val();

    var uri = api + id + "/";

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
    $("#title").text("Edit Booking");

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
    $("#guestId").val(item.guest.id);
    $("#roomId").val(item.room.id);
    $("#comments").val(item.comments);

    // Date conversion TODO!
    if (item.start != null) {
        var date = new Date(item.start[0] + "-" + item.start[1] + "-" + item.start[2]);
        $("#start").val(date.toISOString().substring(0, 10));
    }

    if (item.end != null) {
        var date = new Date(item.end[0] + "-" + item.end[1] + "-" + item.end[2]);
        $("#end").val(date.toISOString().substring(0, 10));
    }

    // Show form
    $("#form").modal('toggle');
}

function save(e) {
    e.preventDefault();

    // Create obj
    var obj = {};
    obj.room = {};
    obj.guest = {};

    obj.id = $("#id").val();
    obj.guest.id = $("#guestId").val();
    obj.room.id = $("#roomId").val();
    obj.comments = $("#comments").val();
    obj.start = $("#start").val();
    obj.end = $("#end").val();

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