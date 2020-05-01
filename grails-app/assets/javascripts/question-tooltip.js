/* question-tooltip.js - add tooltips to id fields question gsps. */
$(document).ready( function() {
    const tooltips = {
        qText:      "Text for question. Can contain ASCII Math formulas within backticks.",
        imgFile:    "Choose a .png file for figure.",
        format:     "Range: 1 - very small,..., 4 - large, 5 - very large.",
        aText:      "Text for answer. Can contain an ASCII Math formula within backticks.",
        difficulty: "1 - easy, 2 - medium, 3 - hard, 4 - very hard.",
        gradeLevel: "6th grade, 7th grade, 8th grade."
    }
    addToolTips(tooltips);
});