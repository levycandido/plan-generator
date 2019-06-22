package com.levycandido.plangenerator.desk.util;

import java.io.File;
import java.text.ParseException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.levycandido.plangenerator.desk.model.Loan;

public class DialogParameter {

	public Loan getParameter() {
		int loanAmount = 0;
		double nominalRate = 0;
		int duration = 0;
		String startDate = null;
		Loan loan = null;

		JTextField loanAmountTextField = new JTextField();
		JTextField nominalRateTextField = new JTextField();
		JTextField durationTextField = new JTextField();
		JTextField startDateTextField = new JTextField();
		Object[] message = { "Loan Amount:", loanAmountTextField, "Nominal Rate:", nominalRateTextField,
				"Duration (in months):", durationTextField, "start Date:", startDateTextField };

		int option = JOptionPane.showConfirmDialog(null, message, "Plan Generator", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			LoanValidator loanValidator = new LoanValidator(loanAmountTextField.getText(),
					nominalRateTextField.getText(), durationTextField.getText(), startDateTextField.getText());
			if (!loanValidator.validate()) {
				JOptionPane.showMessageDialog(null, loanValidator.error);
				Runtime.getRuntime().exit(0);
			}
			loanAmount = Integer.valueOf(loanAmountTextField.getText());
			nominalRate = Double.valueOf(nominalRateTextField.getText());
			duration = Integer.valueOf(durationTextField.getText());
			startDate = startDateTextField.getText();
			try {
				loan = new Loan(loanAmount, nominalRate * 0.01, duration, startDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Application cancelled!");
			Runtime.getRuntime().exit(0);
		}
		return loan;
	}

	public File fileSave() {
		String fileName;
		String dir;
		File file = null;
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.cvs", "csv");
		fileChooser.setFileFilter(filter);
		if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			file = fileChooser.getSelectedFile();
			/** check the extension of the file */
			fileName = checkExtension(file.getName());
			dir = file.getParentFile().getAbsoluteFile().toString();
			file = new File(dir, fileName);
		} else {
			JOptionPane.showMessageDialog(null, "Application cancelled!");
			Runtime.getRuntime().exit(0);
		}
		return file;
	}

	private static String checkExtension(String fileName) {
		// Check a dot on the name
		String ext;
		if (fileName.contains(".")) {
			ext = fileName.substring(fileName.indexOf("."), fileName.length());
			if (!ext.equals(".csv")) {
				fileName = fileName.substring(0, fileName.indexOf(".")) + ".csv";
			}
		} else {
			fileName = fileName + ".csv";
		}
		return fileName;
	}
}
