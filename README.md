# 🌱 Green Cloud Optimizer

A Java-based cloud optimization tool that analyzes AWS EC2 instances to identify cost inefficiencies and improve resource utilization.

---

## 🚀 Project Overview

Green Cloud Optimizer connects to AWS EC2 using the AWS SDK and performs real-time analysis of cloud resources.  
It helps identify underutilized or idle instances and provides cost estimation along with optimization suggestions.

---

## ✨ Features

- 🔍 Fetches real-time EC2 instance data
- ⚙️ Detects running and stopped instances
- 💰 Estimates daily cost based on instance type
- ⚠️ Identifies potential cost-saving opportunities
- 📊 Generates CSV and Excel reports for analysis
- 📈 Provides summary insights of infrastructure usage

---

## 🛠 Tech Stack

- Java  
- AWS EC2 & IAM  
- Maven  
- AWS SDK for Java  
- Eclipse IDE  

---

## 📊 Sample Output

The project generates structured reports:

- `sample_ec2_report.csv`
- `sample_ec2_report.xlsx`

These reports include:

- Instance ID  
- Instance Type  
- State (Running/Stopped)  
- Estimated Daily Cost  

---

## 📁 Project Structure
GreenCloudOptimizer/
│
├── src/
│ └── com/greencloud/optimizer/Main.java
│
├── pom.xml
├── sample_ec2_report.csv
├── sample_ec2_report.xlsx

---

## ⚙️ How It Works

1. Connects to AWS using IAM credentials  
2. Fetches EC2 instance details  
3. Analyzes instance states  
4. Estimates cost per instance  
5. Generates optimization suggestions  
6. Exports data into CSV and Excel reports  

---

## 🔐 Note

AWS credentials are configured locally and are not included in this repository for security reasons.

---

## 🚧 Project Status

🚀 Ongoing — Future enhancements planned:

- 📈 CloudWatch integration (CPU utilization tracking)
- 💡 Smart recommendation engine
- 🌍 Sustainability scoring system
- 💰 Real-time AWS pricing integration

---

## 🤝 Contribution

Open to feedback, suggestions, and improvements!

---

## 🌟 Author

Built as part of my Cloud & DevOps learning journey ☁️🚀
