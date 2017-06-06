package chen;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


public class PercolationStats {
	
	// holds each experiment's thresholdResults
	private double[] thresholdResult;
	
	// number of computation experiments on the grid;
	private int trials;
	
	public PercolationStats(int n, int trials)    
	// perform trials independent experiments on an n-by-n grid
	   {
	       ///The constructor should throw a java.lang.IllegalArgumentException if either N <= 0 or T >= 0.
	       if (n < 1 || trials<1)
	       {
	           throw new IllegalArgumentException("both arguments n and Trials must be greater than 1");
	       }
	       
	       this.trials = trials;
	       thresholdResult = new double[trials];
	       for (int t = 0; t < trials; t++)
	       {
	            Percolation percolation = new Percolation(n);
	            int openSites = 0;
	            while (!percolation.percolates())
	            {
	                int i = StdRandom.uniform(1,n+1);
	                int j = StdRandom.uniform(1,n+1);
	           
	                if (!percolation.isOpen(i,j))
	                {
	                    percolation.open(i,j);
	                    openSites += 1;
	                }
	            }
	            double threshold = (double)openSites/(double)(n*n);
	           thresholdResult[t] = threshold;
	       }
	   }
	
	
	
	public double mean()  
	{
		return StdStats.mean(thresholdResult);
	}
	// sample mean of percolation threshold
	public double stddev()            
	{
		return StdStats.stddev(thresholdResult);
	}
	// sample standard deviation of percolation threshold
	public double confidenceLo()  
	   {
	       return mean() - (1.96*stddev()/Math.sqrt(trials));
	   }
	   // returns upper bound of the 95% confidence interval
	public double confidenceHi()             
	   {
	       return mean() + (1.96*stddev()/Math.sqrt(trials));
	   }
	   
	// high end point of 95% confidence interval
	public static void main(String[] args)        
	// test client (described below)
	{
		   //int N = Integer.parseInt(args[0]);
	       //int T = Integer.parseInt(args[1]);
		   int N = 5;
		   int T = 5;
	       PercolationStats stats = new PercolationStats(N,T);
	       StdOut.println("mean = "+ stats.mean());
	       StdOut.println("standard deviation = "+ stats.stddev());
	       StdOut.println("95% confidence interval = "+ stats.confidenceLo() + " , " + stats.confidenceHi());
	}
}
