    $("#navJobList").click(function (event) {
        event.preventDefault();

        $.ajax({
            url: 'http://localhost:8080/job/jobs',
            type: 'GET',
            success: function (response) {
                // Clear existing table rows
                $('#jobTable').empty();

                // Populate table with received data
                $.each(response, function (index, job) {
                    var newRow = '<tr>' +
                        '<td class="date">' + job.date + '</td>' +
                        '<td class="company">' + job.company + '</td>' +
                        '<td class="country">' + job.country + '</td>' +
                        '<td class="Role">' + job.role + '</td>' +
                        '<td class="response">' +
                        '<select class="responseDropdown">' +
                        '<option value="Waiting">Waiting</option>' +
                        '<option value="Yes">Yes</option>' +
                        '<option value="No">No</option>' +
                        '</select>' +
                        '</td>' +
                        '<td class="go forward">' +
                        '<select class="forwardDropdown">' +
                        '<option value="Waiting">Waiting</option>' +
                        '<option value="Yes">Yes</option>' +
                        '<option value="No">No</option>' +
                        '</select>' +
                        '</td>' +
                        '</tr>';
                    $('#jobTable').append(newRow);
                });
            },
            error: function (error) {
                console.log(error);
                // Handle error response
            }
        });
    });
