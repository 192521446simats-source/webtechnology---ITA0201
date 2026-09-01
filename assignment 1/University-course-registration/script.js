/* =========================================================
   UNIVERSITY COURSE REGISTRATION PORTAL
   JavaScript - Unit II
   ========================================================= */


/* =========================================================
   1. COURSE DATA
   JavaScript Array of Objects
   ========================================================= */

const courses = [

    {
        code: "CS301",
        name: "Data Structures",
        credits: 4,
        type: "Core"
    },

    {
        code: "CS302",
        name: "Database Management Systems",
        credits: 4,
        type: "Core"
    },

    {
        code: "IT303",
        name: "Web Technology",
        credits: 3,
        type: "Core"
    },

    {
        code: "AI304",
        name: "Artificial Intelligence",
        credits: 3,
        type: "Elective"
    },

    {
        code: "CS305",
        name: "Computer Networks",
        credits: 4,
        type: "Core"
    },

    {
        code: "SE306",
        name: "Software Engineering",
        credits: 3,
        type: "Elective"
    },

    {
        code: "IT307",
        name: "Cloud Computing",
        credits: 3,
        type: "Elective"
    },

    {
        code: "CS308",
        name: "Operating Systems",
        credits: 4,
        type: "Core"
    },

    {
        code: "AI309",
        name: "Machine Learning",
        credits: 4,
        type: "Elective"
    },

    {
        code: "IT310",
        name: "Cyber Security",
        credits: 3,
        type: "Elective"
    },

    {
        code: "EC311",
        name: "Digital Electronics",
        credits: 3,
        type: "Core"
    },

    {
        code: "EC312",
        name: "Computer Architecture",
        credits: 4,
        type: "Core"
    },

    {
        code: "DS313",
        name: "Data Science",
        credits: 4,
        type: "Elective"
    },

    {
        code: "CS314",
        name: "Object Oriented Programming",
        credits: 3,
        type: "Core"
    },

    {
        code: "MG315",
        name: "Principles of Management",
        credits: 2,
        type: "Open Elective"
    },

    {
        code: "HS316",
        name: "Professional Ethics",
        credits: 2,
        type: "Open Elective"
    }
];


/* =========================================================
   2. CONFIGURATION
   ========================================================= */

const MAX_CREDITS = 24;
const MIN_COURSES = 1;


/* =========================================================
   3. GET HTML ELEMENTS
   ========================================================= */

const form = document.getElementById("registrationForm");

const registerNumber =
    document.getElementById("registerNumber");

const studentName =
    document.getElementById("studentName");

const email =
    document.getElementById("email");

const department =
    document.getElementById("department");

const semester =
    document.getElementById("semester");

const courseCheckboxes =
    document.querySelectorAll('input[name="course"]');


/* =========================================================
   4. SUMMARY ELEMENTS
   ========================================================= */

const emptySummary =
    document.getElementById("emptySummary");

const registrationSummary =
    document.getElementById("registrationSummary");

const summaryName =
    document.getElementById("summaryName");

const summaryRegister =
    document.getElementById("summaryRegister");

const summaryDepartment =
    document.getElementById("summaryDepartment");

const selectedCourseList =
    document.getElementById("selectedCourseList");

const totalCourses =
    document.getElementById("totalCourses");

const totalCredits =
    document.getElementById("totalCredits");


/* =========================================================
   5. DEBUG PANEL
   This section displays debugging information
   directly inside the website.
   ========================================================= */

function createDebugPanel() {

    if (document.getElementById("debugPanel")) {
        return;
    }

    const debugSection = document.createElement("section");

    debugSection.id = "debugPanel";

    debugSection.innerHTML = `
        <div class="debug-container">

            <div class="debug-header">
                <div>
                    <span class="debug-label">
                        DEVELOPMENT TOOLS
                    </span>

                    <h2>
                        Developer Console & Debugging
                    </h2>

                    <p>
                        Live debugging information generated
                        using JavaScript console and event handling.
                    </p>
                </div>

                <button
                    type="button"
                    id="clearDebug"
                    class="debug-clear"
                >
                    Clear Console
                </button>
            </div>

            <div class="debug-status">
                <span class="status-dot"></span>
                <span id="debugStatus">
                    System initialized successfully
                </span>
            </div>

            <div
                id="debugOutput"
                class="debug-output"
            ></div>

            <div class="debug-note">
                <strong>Developer Tools:</strong>
                Press <kbd>F12</kbd> or
                <kbd>Ctrl</kbd> + <kbd>Shift</kbd> +
                <kbd>I</kbd> and open the
                <strong>Console</strong> tab to view
                JavaScript debugging messages.
            </div>

        </div>
    `;

    document.body.insertBefore(
        debugSection,
        document.querySelector("footer")
    );

    document
        .getElementById("clearDebug")
        .addEventListener("click", function () {

            document.getElementById(
                "debugOutput"
            ).innerHTML = "";

            console.clear();

            addDebugMessage(
                "INFO",
                "Debug console cleared."
            );
        });
}


/* =========================================================
   6. DEBUG MESSAGE FUNCTION
   ========================================================= */

function addDebugMessage(type, message) {

    const output =
        document.getElementById("debugOutput");

    const status =
        document.getElementById("debugStatus");

    if (!output) {
        return;
    }

    const time =
        new Date().toLocaleTimeString();

    const messageElement =
        document.createElement("div");

    messageElement.className =
        "debug-message " +
        type.toLowerCase();

    messageElement.innerHTML = `
        <span class="debug-time">
            ${time}
        </span>

        <span class="debug-type">
            [${type}]
        </span>

        <span class="debug-text">
            ${message}
        </span>
    `;

    output.appendChild(messageElement);

    output.scrollTop =
        output.scrollHeight;

    if (status) {
        status.textContent = message;
    }
}


/* =========================================================
   7. VALIDATE REGISTER NUMBER
   ========================================================= */

function validateRegisterNumber() {

    const value =
        registerNumber.value.trim();

    const error =
        document.getElementById(
            "registerNumberError"
        );

    if (value === "") {

        error.textContent =
            "Register number is required.";

        console.warn(
            "Validation failed: Register number is empty."
        );

        addDebugMessage(
            "ERROR",
            "Register number is required."
        );

        return false;
    }

    if (!/^[A-Za-z0-9]{5,15}$/.test(value)) {

        error.textContent =
            "Enter a valid register number.";

        console.warn(
            "Validation failed: Invalid register number."
        );

        addDebugMessage(
            "ERROR",
            "Invalid register number format."
        );

        return false;
    }

    error.textContent = "";

    addDebugMessage(
        "SUCCESS",
        "Register number validated."
    );

    return true;
}


/* =========================================================
   8. VALIDATE STUDENT NAME
   ========================================================= */

function validateStudentName() {

    const value =
        studentName.value.trim();

    const error =
        document.getElementById(
            "studentNameError"
        );

    if (value === "") {

        error.textContent =
            "Student name is required.";

        addDebugMessage(
            "ERROR",
            "Student name is required."
        );

        return false;
    }

    if (!/^[A-Za-z ]{3,50}$/.test(value)) {

        error.textContent =
            "Enter a valid name using letters only.";

        addDebugMessage(
            "ERROR",
            "Invalid student name."
        );

        return false;
    }

    error.textContent = "";

    addDebugMessage(
        "SUCCESS",
        "Student name validated."
    );

    return true;
}


/* =========================================================
   9. VALIDATE EMAIL
   ========================================================= */

function validateEmail() {

    const value =
        email.value.trim();

    const error =
        document.getElementById(
            "emailError"
        );

    const emailPattern =
        /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    if (value === "") {

        error.textContent =
            "Email is required.";

        addDebugMessage(
            "ERROR",
            "Email address is required."
        );

        return false;
    }

    if (!emailPattern.test(value)) {

        error.textContent =
            "Enter a valid email address.";

        console.warn(
            "Invalid email format:",
            value
        );

        addDebugMessage(
            "ERROR",
            "Invalid email format."
        );

        return false;
    }

    error.textContent = "";

    addDebugMessage(
        "SUCCESS",
        "Email validated successfully."
    );

    return true;
}


/* =========================================================
   10. VALIDATE DEPARTMENT
   ========================================================= */

function validateDepartment() {

    const error =
        document.getElementById(
            "departmentError"
        );

    if (department.value === "") {

        error.textContent =
            "Please select a department.";

        addDebugMessage(
            "ERROR",
            "Department has not been selected."
        );

        return false;
    }

    error.textContent = "";

    addDebugMessage(
        "SUCCESS",
        "Department selected: " +
        department.value
    );

    return true;
}


/* =========================================================
   11. VALIDATE SEMESTER
   ========================================================= */

function validateSemester() {

    const value =
        Number(semester.value);

    const error =
        document.getElementById(
            "semesterError"
        );

    if (!semester.value) {

        error.textContent =
            "Please select a semester.";

        addDebugMessage(
            "ERROR",
            "Semester has not been selected."
        );

        return false;
    }

    if (value < 1 || value > 8) {

        error.textContent =
            "Semester must be between 1 and 8.";

        addDebugMessage(
            "ERROR",
            "Invalid semester. Allowed range: 1-8."
        );

        return false;
    }

    error.textContent = "";

    addDebugMessage(
        "SUCCESS",
        "Semester validated: Semester " +
        value
    );

    return true;
}


/* =========================================================
   12. GET SELECTED COURSES
   ========================================================= */

function getSelectedCourses() {

    const selectedCodes = [];

    courseCheckboxes.forEach(
        function (checkbox) {

            if (checkbox.checked) {

                selectedCodes.push(
                    checkbox.value
                );
            }
        }
    );

    /*
        Array.filter() is used to find
        matching course objects.
    */

    const selectedCourses =
        courses.filter(
            function (course) {

                return selectedCodes.includes(
                    course.code
                );
            }
        );

    console.log(
        "Selected course codes:",
        selectedCodes
    );

    console.log(
        "Selected course objects:",
        selectedCourses
    );

    return selectedCourses;
}


/* =========================================================
   13. VALIDATE COURSE SELECTION
   ========================================================= */

function validateCourses() {

    const selectedCourses =
        getSelectedCourses();

    const error =
        document.getElementById(
            "courseError"
        );

    if (
        selectedCourses.length <
        MIN_COURSES
    ) {

        error.textContent =
            "Please select at least one course.";

        addDebugMessage(
            "ERROR",
            "No courses selected."
        );

        return false;
    }

    error.textContent = "";

    addDebugMessage(
        "SUCCESS",
        selectedCourses.length +
        " course(s) selected."
    );

    return true;
}


/* =========================================================
   14. CALCULATE TOTALS
   Reusable JavaScript function
   ========================================================= */

function calculateRegistrationTotals(
    selectedCourses
) {

    let creditTotal = 0;

    selectedCourses.forEach(
        function (course) {

            creditTotal +=
                course.credits;
        }
    );

    const totals = {

        numberOfCourses:
            selectedCourses.length,

        totalCredits:
            creditTotal
    };

    console.log(
        "Registration totals:",
        totals
    );

    return totals;
}


/* =========================================================
   15. VALIDATE CREDIT LIMIT
   ========================================================= */

function validateCreditLimit() {

    const selectedCourses =
        getSelectedCourses();

    const totals =
        calculateRegistrationTotals(
            selectedCourses
        );

    const error =
        document.getElementById(
            "courseError"
        );

    if (
        totals.totalCredits >
        MAX_CREDITS
    ) {

        error.textContent =
            `Maximum credit limit is ${MAX_CREDITS}. ` +
            `You selected ${totals.totalCredits} credits.`;

        addDebugMessage(
            "ERROR",
            "Credit limit exceeded: " +
            totals.totalCredits +
            " / " +
            MAX_CREDITS
        );

        return false;
    }

    if (selectedCourses.length > 0) {

        error.textContent = "";

        addDebugMessage(
            "INFO",
            "Credit limit checked: " +
            totals.totalCredits +
            " / " +
            MAX_CREDITS
        );
    }

    return true;
}


/* =========================================================
   16. DISPLAY SELECTED COURSES
   ========================================================= */

function displaySelectedCourses() {

    const selectedCourses =
        getSelectedCourses();

    selectedCourseList.innerHTML = "";

    selectedCourses.forEach(
        function (course) {

            const listItem =
                document.createElement("li");

            listItem.textContent =
                `${course.code} - ` +
                `${course.name} ` +
                `(${course.credits} Credits)`;

            selectedCourseList.appendChild(
                listItem
            );
        }
    );

    const totals =
        calculateRegistrationTotals(
            selectedCourses
        );

    totalCourses.textContent =
        totals.numberOfCourses;

    totalCredits.textContent =
        totals.totalCredits;

    if (
        selectedCourses.length > 0
    ) {

        emptySummary.classList.add(
            "hidden"
        );

        registrationSummary.classList.remove(
            "hidden"
        );

    } else {

        emptySummary.classList.remove(
            "hidden"
        );

        registrationSummary.classList.add(
            "hidden"
        );
    }

    console.log(
        "Selected Courses:",
        selectedCourses
    );

    console.log(
        "Total Courses:",
        totals.numberOfCourses
    );

    console.log(
        "Total Credits:",
        totals.totalCredits
    );
}


/* =========================================================
   17. UPDATE REGISTRATION SUMMARY
   ========================================================= */

function updateRegistrationSummary() {

    summaryName.textContent =
        studentName.value.trim() || "-";

    summaryRegister.textContent =
        registerNumber.value.trim() || "-";

    summaryDepartment.textContent =
        department.value || "-";

    displaySelectedCourses();

    addDebugMessage(
        "INFO",
        "Registration summary updated dynamically."
    );
}


/* =========================================================
   18. LIVE COURSE SELECTION
   ========================================================= */

courseCheckboxes.forEach(
    function (checkbox) {

        checkbox.addEventListener(
            "change",
            function () {

                updateRegistrationSummary();

                validateCreditLimit();
            }
        );
    }
);


/* =========================================================
   19. LIVE FORM UPDATE
   ========================================================= */

studentName.addEventListener(
    "input",
    updateRegistrationSummary
);

registerNumber.addEventListener(
    "input",
    updateRegistrationSummary
);

department.addEventListener(
    "change",
    updateRegistrationSummary
);


/* =========================================================
   20. LIVE VALIDATION
   ========================================================= */

registerNumber.addEventListener(
    "blur",
    validateRegisterNumber
);

studentName.addEventListener(
    "blur",
    validateStudentName
);

email.addEventListener(
    "blur",
    validateEmail
);

department.addEventListener(
    "change",
    validateDepartment
);

semester.addEventListener(
    "change",
    validateSemester
);


/* =========================================================
   21. FORM SUBMISSION
   ========================================================= */

form.addEventListener(
    "submit",
    function (event) {

        /*
            Prevent browser page reload.
        */

        event.preventDefault();

        console.log(
            "Registration submission started..."
        );

        addDebugMessage(
            "INFO",
            "Registration validation started."
        );


        /*
            Run all validations.
        */

        const validRegister =
            validateRegisterNumber();

        const validName =
            validateStudentName();

        const validEmail =
            validateEmail();

        const validDepartment =
            validateDepartment();

        const validSemester =
            validateSemester();

        const validCourses =
            validateCourses();

        const validCredits =
            validateCreditLimit();


        /*
            Check all validation results.
        */

        if (
            !validRegister ||
            !validName ||
            !validEmail ||
            !validDepartment ||
            !validSemester ||
            !validCourses ||
            !validCredits
        ) {

            console.error(
                "Registration failed because validation errors exist."
            );

            addDebugMessage(
                "ERROR",
                "Registration failed. Please correct the highlighted errors."
            );

            return;
        }


        /*
            Update summary.
        */

        updateRegistrationSummary();


        /*
            Get final selected courses.
        */

        const selectedCourses =
            getSelectedCourses();

        const totals =
            calculateRegistrationTotals(
                selectedCourses
            );


        /*
            Create registration object.
        */

        const registrationData = {

            studentName:
                studentName.value.trim(),

            registerNumber:
                registerNumber.value.trim(),

            email:
                email.value.trim(),

            department:
                department.value,

            semester:
                semester.value,

            selectedCourses:
                selectedCourses,

            totalCourses:
                totals.numberOfCourses,

            totalCredits:
                totals.totalCredits
        };


        /*
            Display data in browser console.
        */

        console.log(
            "========== REGISTRATION DATA =========="
        );

        console.log(
            registrationData
        );

        console.log(
            "Student:",
            registrationData.studentName
        );

        console.log(
            "Register Number:",
            registrationData.registerNumber
        );

        console.log(
            "Department:",
            registrationData.department
        );

        console.log(
            "Selected Courses:",
            registrationData.selectedCourses
        );

        console.log(
            "Total Credits:",
            registrationData.totalCredits
        );

        console.log(
            "======================================="
        );


        /*
            Debug panel message.
        */

        addDebugMessage(
            "SUCCESS",
            "Registration completed successfully."
        );

        addDebugMessage(
            "INFO",
            "Total courses: " +
            totals.numberOfCourses
        );

        addDebugMessage(
            "INFO",
            "Total credits: " +
            totals.totalCredits
        );


        /*
            Show success alert.
        */

        alert(
            "Registration completed successfully!"
        );
    }
);


/* =========================================================
   22. RESET FORM
   ========================================================= */

document
    .getElementById("resetButton")
    .addEventListener(
        "click",
        function () {

            setTimeout(
                function () {

                    /*
                        Clear error messages.
                    */

                    document
                        .querySelectorAll(".error")
                        .forEach(
                            function (error) {

                                error.textContent =
                                    "";
                            }
                        );


                    /*
                        Hide summary.
                    */

                    emptySummary.classList.remove(
                        "hidden"
                    );

                    registrationSummary.classList.add(
                        "hidden"
                    );


                    /*
                        Reset course list.
                    */

                    selectedCourseList.innerHTML =
                        "";

                    totalCourses.textContent =
                        "0";

                    totalCredits.textContent =
                        "0";


                    /*
                        Console debugging.
                    */

                    console.log(
                        "Registration form reset."
                    );

                    addDebugMessage(
                        "INFO",
                        "Registration form reset successfully."
                    );

                },
                0
            );
        }
    );


/* =========================================================
   23. KEYBOARD DEBUGGING SHORTCUT
   ========================================================= */

document.addEventListener(
    "keydown",
    function (event) {

        /*
            Ctrl + Shift + D
            opens/scrolls to debugging section.
        */

        if (
            event.ctrlKey &&
            event.shiftKey &&
            event.key.toLowerCase() === "d"
        ) {

            const debugPanel =
                document.getElementById(
                    "debugPanel"
                );

            if (debugPanel) {

                debugPanel.scrollIntoView({
                    behavior: "smooth"
                });

                addDebugMessage(
                    "INFO",
                    "Developer debugging panel opened."
                );
            }
        }
    }
);


/* =========================================================
   24. INITIALIZE DEBUGGING
   ========================================================= */

createDebugPanel();


/* =========================================================
   25. INITIAL DEBUG MESSAGES
   ========================================================= */

console.log(
    "======================================"
);

console.log(
    "University Course Registration Portal"
);

console.log(
    "JavaScript loaded successfully."
);

console.log(
    "Available courses:",
    courses
);

console.log(
    "Maximum credit limit:",
    MAX_CREDITS
);

console.log(
    "Developer debugging enabled."
);

console.log(
    "======================================"
);


addDebugMessage(
    "SUCCESS",
    "University Course Registration Portal loaded successfully."
);

addDebugMessage(
    "INFO",
    `${courses.length} courses available in the course database.`
);

addDebugMessage(
    "INFO",
    `Maximum credit limit: ${MAX_CREDITS}`
);

addDebugMessage(
    "INFO",
    "Client-side validation system ready."
);

addDebugMessage(
    "INFO",
    "Dynamic registration summary ready."
);