    $("#submit").click(function(event) {
        event.preventDefault();

        // Create FormData object to send form data including file
        var formData = new FormData();
        formData.append('email', $("#email").val());
        formData.append('role', $("#jobName").val());
        formData.append('company', $("#companyName").val());
        formData.append('country', $("#countryName").val());
        formData.append('content', $("#content").val());
        formData.append('attachment', $("#resumeInput")[0].files[0]);

        $.ajax({
            url: 'http://localhost:8080/job/newJob',
            type: 'POST',
            data: formData,
            processData: false, // Prevent jQuery from processing the data
            contentType: false, // Prevent jQuery from setting contentType
            success: function(response) {
                console.log(response);
                // Handle success response
            },
            error: function(error) {
                console.log(error);
                // Handle error response
            }
        });
    });
