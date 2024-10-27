<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ParkMe - Book Parking</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        html, body {
            height: 100%;
        }
        body {
            font-family: 'Arial', sans-serif;
            line-height: 1.6;
            color: #333;
            background-image: url('https://images.unsplash.com/photo-1590674899484-d5640e854abe?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1740&q=80');
            background-size: cover;
            background-position: center;
            background-attachment: fixed;
            display: flex;
            flex-direction: column;
        }
        .container {
            width: 90%;
            max-width: 1200px;
            margin: 0 auto;
            padding: 0 20px;
        }
        header {
            background-color: rgba(0, 123, 255, 0.9);
            color: #fff;
            padding: 1rem 0;
        }
        .header-content {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .logo {
            font-size: 1.5rem;
            font-weight: bold;
        }
        nav ul {
            display: flex;
            list-style: none;
        }
        nav ul li {
            margin-left: 20px;
        }
        nav ul li a {
            color: #fff;
            text-decoration: none;
            padding: 0.5rem 1rem;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }
        nav ul li a:hover {
            background-color: rgba(0, 86, 179, 0.7);
        }
        main {
            flex: 1 0 auto;
            padding: 2rem 0;
        }
        .card {
            background-color: rgba(255, 255, 255, 0.9);
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            padding: 2rem;
            margin-bottom: 2rem;
            max-width: 500px;
            margin-left: auto;
            margin-right: auto;
        }
        h1 {
            color: #007bff;
            margin-bottom: 1rem;
            text-align: center;
        }
        form {
            display: grid;
            gap: 1rem;
        }
        label {
            font-weight: bold;
        }
        select, input[type="datetime-local"] {
            width: 100%;
            padding: 0.5rem;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        input[type="submit"] {
            background-color: #28a745;
            color: #fff;
            padding: 0.75rem 1.5rem;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        input[type="submit"]:hover {
            background-color: #218838;
        }
        .error {
            color: #dc3545;
            background-color: #f8d7da;
            border: 1px solid #f5c6cb;
            padding: 0.75rem;
            border-radius: 5px;
            margin-bottom: 1rem;
        }
        footer {
            background-color: rgba(51, 51, 51, 0.9);
            color: #fff;
            text-align: center;
            padding: 1rem 0;
            flex-shrink: 0;
        }
        .booking-icon {
            text-align: center;
            margin-bottom: 1rem;
        }
        .booking-icon img {
            width: 64px;
            height: 64px;
        }
    </style>
    <script>
        $(document).ready(function() {
            // Fetch and populate cars dropdown
            $.ajax({
                url: "${pageContext.request.contextPath}/BookingServlet",
                type: "GET",
                data: {action: "getCars"},
                success: function(data) {
                    var carDropdown = $('#car');
                    carDropdown.empty();
                    carDropdown.append('<option value="">Select a car</option>');
                    $.each(data, function(index, car) {
                        carDropdown.append('<option value="' + car.id + '">' + car.make + ' ' + car.model + ' (' + car.licensePlate + ')</option>');
                    });
                },
                error: function() {
                    alert("Error loading cars.");
                }
            });

            // Fetch and populate parking spots dropdown
            $.ajax({
                url: "${pageContext.request.contextPath}/BookingServlet",
                type: "GET",
                data: {action: "getParkingSpots"},
                success: function(data) {
                    var parkingDropdown = $('#parkingSpot');
                    parkingDropdown.empty();
                    parkingDropdown.append('<option value="">Select a parking spot</option>');
                    $.each(data, function(index, spot) {
                        parkingDropdown.append('<option value="' + spot.id + '">' + spot.location + '</option>');
                    });
                },
                error: function() {
                    alert("Error loading parking spots.");
                }
            });
        });
    </script>
</head>
<body>
    <header>
        <div class="container">
            <div class="header-content">
                <div class="logo">ParkMe</div>
                <nav>
                    <ul>
                        <li><a href="index.jsp">Home</a></li>
                        <li><a href="addCar.jsp">Add Car</a></li>
                        <li><a href="addParking.jsp">Add Parking</a></li>
                        <li><a href="booking.jsp">Book Parking</a></li>
                        <li><a href="viewBookings.jsp">View Bookings</a></li>
                    </ul>
                </nav>
            </div>
        </div>
    </header>
    <main>
        <div class="container">
            <div class="card">
                <div class="booking-icon">
                    <img src="https://img.icons8.com/ios-filled/100/000000/parking.png" alt="Booking icon">
                </div>
                <h1>Book a Parking Spot</h1>
                <form action="${pageContext.request.contextPath}/BookingServlet" method="post">
                    <div>
                        <label for="car">Select Car:</label>
                        <select id="car" name="carId" required>
                            <option value="">Loading cars...</option>
                        </select>
                    </div>
                    <div>
                        <label for="parkingSpot">Select Parking Spot:</label>
                        <select id="parkingSpot" name="parkingSpotId" required>
                            <option value="">Loading parking spots...</option>
                        </select>
                    </div>
                    <div>
                        <label for="startTime">Start Time:</label>
                        <input type="datetime-local" id="startTime" name="startTime" required>
                    </div>
                    <div>
                        <label for="endTime">End Time:</label>
                        <input type="datetime-local" id="endTime" name="endTime" required>
                    </div>
                    <div>
                        <input type="submit" value="Book Parking">
                    </div>
                </form>
                <c:if test="${not empty error}">
                    <p class="error">${error}</p>
                </c:if>
            </div>
        </div>
    </main>
    <footer>
        <div class="container">
            <p>&copy; 2023 ParkMe. All rights reserved.</p>
            <p>Contact us: support@parkme.com | (123) 456-7890</p>
        </div>
    </footer>
</body>
</html>