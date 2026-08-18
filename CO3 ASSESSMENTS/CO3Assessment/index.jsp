<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Result Processing</title>

    <style>
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
        }

        body {
            min-height: 100vh;
            background: linear-gradient(135deg, #dbeafe, #f8fafc);
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 30px;
        }

        .container {
            width: 700px;
            background: white;
            padding: 35px;
            border-radius: 18px;
            box-shadow: 0 10px 35px rgba(0, 0, 0, 0.15);
        }

        h1 {
            text-align: center;
            color: #1e3a8a;
            margin-bottom: 10px;
        }

        .subtitle {
            text-align: center;
            color: #64748b;
            margin-bottom: 25px;
        }

        .section-title {
            color: #1e40af;
            margin: 20px 0 12px;
        }

        .form-group {
            margin-bottom: 15px;
        }

        label {
            display: block;
            margin-bottom: 6px;
            font-weight: bold;
            color: #334155;
        }

        input {
            width: 100%;
            padding: 12px;
            border: 1px solid #cbd5e1;
            border-radius: 8px;
            font-size: 15px;
        }

        input:focus {
            outline: none;
            border-color: #2563eb;
        }

        .subjects {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 15px;
        }

        button {
            width: 100%;
            padding: 14px;
            margin-top: 20px;
            border: none;
            border-radius: 8px;
            background: #2563eb;
            color: white;
            font-size: 17px;
            font-weight: bold;
            cursor: pointer;
        }

        button:hover {
            background: #1d4ed8;
        }

        @media (max-width: 600px) {
            .container {
                width: 100%;
            }

            .subjects {
                grid-template-columns: 1fr;
            }
        }
    </style>
</head>

<body>

<div class="container">

    <h1>Student Result Processing</h1>

    <p class="subtitle">
        Enter student details and marks for five subjects
    </p>

    <form action="ResultServlet" method="post">

        <h2 class="section-title">Student Details</h2>

        <div class="form-group">
            <label for="name">Student Name</label>

            <input type="text"
                   id="name"
                   name="name"
                   placeholder="Enter student name"
                   required>
        </div>

        <div class="form-group">
            <label for="registerNo">Register Number</label>

            <input type="text"
                   id="registerNo"
                   name="registerNo"
                   placeholder="Enter register number"
                   required>
        </div>

        <h2 class="section-title">Subject Marks</h2>

        <div class="subjects">

            <div class="form-group">
                <label for="mark1">Subject 1</label>

                <input type="number"
                       id="mark1"
                       name="mark1"
                       min="0"
                       max="100"
                       placeholder="0 - 100"
                       required>
            </div>

            <div class="form-group">
                <label for="mark2">Subject 2</label>

                <input type="number"
                       id="mark2"
                       name="mark2"
                       min="0"
                       max="100"
                       placeholder="0 - 100"
                       required>
            </div>

            <div class="form-group">
                <label for="mark3">Subject 3</label>

                <input type="number"
                       id="mark3"
                       name="mark3"
                       min="0"
                       max="100"
                       placeholder="0 - 100"
                       required>
            </div>

            <div class="form-group">
                <label for="mark4">Subject 4</label>

                <input type="number"
                       id="mark4"
                       name="mark4"
                       min="0"
                       max="100"
                       placeholder="0 - 100"
                       required>
            </div>

            <div class="form-group">
                <label for="mark5">Subject 5</label>

                <input type="number"
                       id="mark5"
                       name="mark5"
                       min="0"
                       max="100"
                       placeholder="0 - 100"
                       required>
            </div>

        </div>

        <button type="submit">
            Calculate Result
        </button>

    </form>

</div>

</body>
</html>