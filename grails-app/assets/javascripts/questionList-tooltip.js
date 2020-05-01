/* questionList-tooltip.js - add tooltips to id fields question gsps. */
$(document).ready( function() {
    const tooltips = {
        idFilter:         "Check if you want to filter by Question id range.",
        idStart:          "Starting id #.",
        idEnd:            "Ending id #.",
        difficultyFilter: "Check if you want to filter by difficulty.",
        difficulty:       "Select a difficulty level.",
        gradeLevelFilter: "Check if you want to filter by grade level.",
        gradeLevel:       "Select a grade level."
    }
    addToolTips(tooltips);
});