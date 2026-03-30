package com.greencloud.optimizer;
import java.io.FileWriter;
import java.io.IOException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;

public class Main {

    public static void main(String[] args) {

        System.out.println("Connecting to AWS EC2...");

        // Create EC2 client
        AmazonEC2 ec2 = AmazonEC2ClientBuilder.standard()
                .withRegion(Regions.US_EAST_1) // Change if your region is different
                .withCredentials(new ProfileCredentialsProvider())
                .build();

        System.out.println("Connected Successfully!\n");

        // Fetch EC2 instances
        DescribeInstancesResult result = ec2.describeInstances();
      
        System.out.println("Fetching EC2 Instance Details...\n");
        if (result.getReservations().isEmpty()) {
            System.out.println("No EC2 instances found in this region.");
        }

        int totalInstances = 0;
        int runningInstances = 0;
        int stoppedInstances = 0;
        FileWriter writer = null;

        try {
            writer = new FileWriter("ec2_report.csv");

            // Header
            writer.append("Instance ID,Instance Type,State,Daily Cost\n");

        for (Reservation reservation : result.getReservations()) {
            for (Instance instance : reservation.getInstances()) {

                totalInstances++;

                String instanceId = instance.getInstanceId();
                String instanceType = instance.getInstanceType();
                double dailyCost = getEstimatedDailyCost(instanceType);
                String state = instance.getState().getName();

                System.out.println("Instance ID: " + instanceId);
                System.out.println("Instance Type: " + instanceType);
                System.out.println("Estimated Daily Cost: $" + dailyCost);
                System.out.println("State: " + state);
                writer.append(instanceId + "," + instanceType + "," + state + "," + dailyCost + "\n");

                // Optimization logic with cost insight
                if (state.equalsIgnoreCase("stopped")) {
                    stoppedInstances++;
                    System.out.println("Optimization Suggestion: Instance is stopped. Consider terminating to save costs.");
                    System.out.println("Potential Waste: $" + dailyCost + " per day");
                } 
                else if (state.equalsIgnoreCase("running")) {
                    runningInstances++;
                    System.out.println("Optimization Suggestion: Instance is running. Verify if it's actively being used.");
                } 
                else {
                    System.out.println("ℹ Instance state: " + state);
                }

                System.out.println("-----------------------------------");
            }
        }
        writer.flush();
        writer.close();

        System.out.println("CSV report generated: ec2_report.csv");

    } catch (IOException e) {
        e.printStackTrace();
    }
       

        // Summary Report
        System.out.println("\n===== GREEN CLOUD SUMMARY REPORT =====");
        System.out.println("Total Instances: " + totalInstances);
        System.out.println("Running Instances: " + runningInstances);
        System.out.println("Stopped Instances: " + stoppedInstances);

        if (stoppedInstances > 0) {
            System.out.println("Potential Cost Saving Opportunity Detected!");
        } else {
            System.out.println("No immediate stopped-instance waste detected.");
        }

        System.out.println("\nAnalysis Complete.");
    }
    
    public static double getEstimatedDailyCost(String instanceType) {

        switch (instanceType) {
            case "t2.micro":
                return 0.28;
            case "t2.small":
                return 0.50;
            case "t2.medium":
                return 1.00;
            default:
                return 0.75; // default estimate
        }
    }
}