package com.levycandido.plangenerator.service;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

public class PlanPrinter {

	private Plan plan;

	public PlanPrinter(Plan plan) {
		this.plan = plan;
	}

	public String getPaymentCSV() {
		StringBuilder sb = new StringBuilder();
		sb.append("Date|Annuity (Borrower Payment Amount)|Principal|Interest|initial Outstanding Principal|"
				+ "remaining Outstanding Principal\n");
		for (Payment payment : plan.getPayments()) {
			sb.append(exportToCSV(payment));
			sb.append("\n");
		}
		return sb.toString();
	}

	public void printPaymentPlan(File file) {
		/** Delete the file case it already exists */
		if (file.exists()) {
			file.delete();
		}
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		writePaymentPlan(file);
		int option = JOptionPane.showConfirmDialog(null, "CVS file exported successfully. Would you like to open it?",
				"Plan Generator", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			openfile(file);
		}
	}

	private void openfile(File file) {
		try {
			Desktop desktop = null;
			if (Desktop.isDesktopSupported()) {
				desktop = Desktop.getDesktop();
			}
			desktop.open(file);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}

	public String exportToCSV(Payment payment) {
		SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyyy");
		DecimalFormat df = new DecimalFormat("#.##");
		return f.format(payment.getPaymentDate()) + "|" + df.format(payment.getPaymentAmount()) + "|"
				+ df.format(payment.getPrincipal()) + "|" + df.format(payment.getInterest()) + "|"
				+ df.format(payment.getInitial()) + "|" + df.format(Math.abs(payment.getRemaining()));
	}

	private boolean writePaymentPlan(File file) {
		String fileContents = getPaymentCSV();
		byte[] str = fileContents.getBytes();
		try {
			Files.write(file.toPath(), str);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
