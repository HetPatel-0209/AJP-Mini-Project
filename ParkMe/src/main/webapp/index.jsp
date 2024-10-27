<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ParkMe - Your Parking Solution</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        body {
            font-family: 'Arial', sans-serif;
            line-height: 1.6;
            color: #333;
        }
        .container {
            width: 90%;
            max-width: 1200px;
            margin: 0 auto;
            padding: 0 20px;
        }
        header {
            background-color: #fff;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
            padding: 1rem 0;
        }
        nav {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .logo {
            font-size: 1.5rem;
            font-weight: bold;
            color: #333;
        }
        nav ul {
            display: flex;
            list-style: none;
        }
        nav ul li {
            margin-left: 20px;
        }
        nav ul li a {
            text-decoration: none;
            color: #333;
        }
        .hero {
            background-image: url('https://images.unsplash.com/photo-1506521781263-d8422e82f27a?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1740&q=80');
            background-size: cover;
            background-position: center;
            height: 500px;
            display: flex;
            align-items: center;
            text-align: center;
            color: #fff;
        }
        .hero-content {
            background-color: rgba(0,0,0,0.6);
            padding: 2rem;
            border-radius: 10px;
        }
        .hero h1 {
            font-size: 2.5rem;
            margin-bottom: 1rem;
        }
        .btn {
            display: inline-block;
            background-color: #007bff;
            color: #fff;
            padding: 0.75rem 1.5rem;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }
        .btn:hover {
            background-color: #0056b3;
        }
        .features {
            padding: 4rem 0;
            background-color: #f8f9fa;
        }
        .features h2 {
            text-align: center;
            margin-bottom: 2rem;
        }
        .feature-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 2rem;
        }
        .feature {
            text-align: center;
        }
        .feature img {
            width: 64px;
            height: 64px;
            margin-bottom: 1rem;
        }
        .how-it-works {
            padding: 4rem 0;
        }
        .how-it-works h2 {
            text-align: center;
            margin-bottom: 2rem;
        }
        .steps {
            display: flex;
            justify-content: space-around;
            flex-wrap: wrap;
        }
        .step {
            text-align: center;
            max-width: 200px;
        }
        .step-number {
            background-color: #007bff;
            color: #fff;
            width: 40px;
            height: 40px;
            border-radius: 50%;
            display: flex;
            justify-content: center;
            align-items: center;
            margin: 0 auto 1rem;
            font-weight: bold;
        }
        .testimonials {
            background-color: #f8f9fa;
            padding: 4rem 0;
        }
        .testimonials h2 {
            text-align: center;
            margin-bottom: 2rem;
        }
        .testimonial-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
            gap: 2rem;
        }
        .testimonial {
            background-color: #fff;
            padding: 1.5rem;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
        }
        footer {
            background-color: #333;
            color: #fff;
            padding: 2rem 0;
            text-align: center;
        }
        .action-buttons {
            background-color: #f0f0f0;
            padding: 2rem 0;
            text-align: center;
        }
        .action-buttons .btn {
            margin: 0.5rem;
        }
    </style>
</head>
<body>
    <header>
        <nav class="container">
            <div class="logo">ParkMe</div>
            <ul>
                <li><a href="#home">Home</a></li>
                <li><a href="#features">Features</a></li>
                <li><a href="#how-it-works">How It Works</a></li>
                <li><a href="#testimonials">Testimonials</a></li>
                <li><a href="addCar.jsp">Add Car</a></li>
                <li><a href="addParking.jsp">Add Parking</a></li>
                <li><a href="booking.jsp">Book Parking</a></li>
                <li><a href="viewBookings.jsp">View Bookings</a></li>
            </ul>
        </nav>
    </header>

    <main>
        <section id="home" class="hero">
            <div class="container">
                <div class="hero-content">
                    <h1>Your Parking Solution</h1>
                    <p>ParkMe provides an easy and efficient way to find and book parking spots.</p>
                    <a href="#" class="btn">Get Started</a>
                </div>
            </div>
        </section>

        <section class="action-buttons">
            <div class="container">
                <h2>Quick Actions</h2>
                <a href="addCar.jsp" class="btn">Add Car</a>
                <a href="addParking.jsp" class="btn">Add Parking</a>
                <a href="booking.jsp" class="btn">Book Parking</a>
                <a href="viewBookings.jsp" class="btn">View Bookings</a>
            </div>
        </section>

        <section id="features" class="features">
            <div class="container">
                <h2>How We Help Our Users</h2>
                <div class="feature-grid">
                    <div class="feature">
                        <img src="https://img.icons8.com/ios/100/000000/clock--v1.png" alt="Clock icon">
                        <h3>Save Time</h3>
                        <p>Quickly locate available parking spots near your destination.</p>
                    </div>
                    <div class="feature">
                        <img src="https://img.icons8.com/ios/100/000000/map-marker--v1.png" alt="Map marker icon">
                        <h3>Reduce Stress</h3>
                        <p>Guarantee a parking space before you even leave home.</p>
                    </div>
                    <div class="feature">
                        <img src="https://img.icons8.com/ios/100/000000/bank-card-back-side--v1.png" alt="Credit card icon">
                        <h3>Easy Management</h3>
                        <p>Manage your vehicles and bookings all in one place.</p>
                    </div>
                </div>
            </div>
        </section>

        <section id="how-it-works" class="how-it-works">
            <div class="container">
                <h2>How It Works</h2>
                <div class="steps">
                    <div class="step">
                        <div class="step-number">1</div>
                        <h3>Register</h3>
                        <p>Create your account and add your vehicle details.</p>
                    </div>
                    <div class="step">
                        <div class="step-number">2</div>
                        <h3>Search</h3>
                        <p>Find available parking spots near your destination.</p>
                    </div>
                    <div class="step">
                        <div class="step-number">3</div>
                        <h3>Book</h3>
                        <p>Reserve your parking spot with just a few clicks.</p>
                    </div>
                    <div class="step">
                        <div class="step-number">4</div>
                        <h3>Park</h3>
                        <p>Arrive at your reserved spot and enjoy your hassle-free parking.</p>
                    </div>
                </div>
            </div>
        </section>

        <section id="testimonials" class="testimonials">
            <div class="container">
                <h2>What Our Users Say</h2>
                <div class="testimonial-grid">
                    <div class="testimonial">
                        <p>"ParkMe has made finding parking in the city so much easier. I never have to worry about circling the block anymore!"</p>
                        <p><strong>- Sarah J.</strong></p>
                    </div>
                    <div class="testimonial">
                        <p>"As a business traveler, ParkMe saves me time and reduces stress. It's a game-changer for urban parking."</p>
                        <p><strong>- Michael T.</strong></p>
                    </div>
                    <div class="testimonial">
                        <p>"I love how easy it is to manage my vehicles and bookings. ParkMe has simplified my parking experience."</p>
                        <p><strong>- Emily R.</strong></p>
                    </div>
                </div>
            </div>
        </section>
    </main>

    <footer>
        <div class="container">
            <p>&copy; 2023 ParkMe. All rights reserved.</p>
        </div>
    </footer>
</body>
</html>