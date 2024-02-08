$(document).ready(function() {
    // Load initial tab content
    loadTabContent("#inputJobs", "tabs/inputInfos.html");
    loadTabContent("#jobList", "tabs/jobList.html");

    // Handle tab navigation
    $("#navJobList").click(function() {
        switchTabs("#inputJobs", "#jobList");
    });

    $("#navInputJobs").click(function() {
        switchTabs("#jobList", "#inputJobs");
    });
});

function loadTabContent(tabId, contentUrl) {
    $(tabId).load(contentUrl);
}

function switchTabs(tabToHide, tabToShow) {
    $(tabToHide).removeClass('active');
    $(tabToShow).addClass('active');
}

  

