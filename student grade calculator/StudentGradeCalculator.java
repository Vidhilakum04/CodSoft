import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentGradeCalculator extends JFrame implements ActionListener {
    private JLabel[] subjectLabels;
    private JTextField[] subjectFields;
    private JButton calculateButton;
    private JLabel totalMarksLabel;
    private JLabel averagePercentageLabel;
    private JLabel gradeLabel;

    public StudentGradeCalculator() {
        setTitle("Student Grade Calculator");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        int numSubjects = 5; // Change this to adjust the number of subjects

        subjectLabels = new JLabel[numSubjects];
        subjectFields = new JTextField[numSubjects];

        int startY = 20;
        for (int i = 0; i < numSubjects; i++) {
            subjectLabels[i] = new JLabel("Subject " + (i + 1) + " Marks:");
            subjectLabels[i].setBounds(30, startY + i * 30, 100, 20);
            add(subjectLabels[i]);

            subjectFields[i] = new JTextField();
            subjectFields[i].setBounds(150, startY + i * 30, 100, 20);
            add(subjectFields[i]);
        }

        calculateButton = new JButton("Calculate");
        calculateButton.setBounds(150, startY + numSubjects * 30 + 10, 100, 30);
        calculateButton.addActionListener(this);
        add(calculateButton);

        totalMarksLabel = new JLabel();
        totalMarksLabel.setBounds(30, startY + (numSubjects + 1) * 30 + 20, 300, 20);
        add(totalMarksLabel);

        averagePercentageLabel = new JLabel();
        averagePercentageLabel.setBounds(30, startY + (numSubjects + 2) * 30 + 20, 300, 20);
        add(averagePercentageLabel);

        gradeLabel = new JLabel();
        gradeLabel.setBounds(30, startY + (numSubjects + 3) * 30 + 20, 300, 20);
        add(gradeLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculateButton) {
            int totalMarks = 0;
            int numSubjects = subjectFields.length;

            for (int i = 0; i < numSubjects; i++) {
                try {
                    int marks = Integer.parseInt(subjectFields[i].getText());
                    totalMarks += marks;
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Please enter valid marks for all subjects.");
                    return;
                }
            }

            double averagePercentage = (double) totalMarks / numSubjects;

            String grade;
            if (averagePercentage >= 90) {
                grade = "A";
            } else if (averagePercentage >= 80) {
                grade = "B";
            } else if (averagePercentage >= 70) {
                grade = "C";
            } else if (averagePercentage >= 60) {
                grade = "D";
            } else {
                grade = "F";
            }

            totalMarksLabel.setText("Total Marks: " + totalMarks);
            averagePercentageLabel.setText("Average Percentage: " + String.format("%.2f", averagePercentage));
            gradeLabel.setText("Grade: " + grade);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                StudentGradeCalculator calculator = new StudentGradeCalculator();
                calculator.setVisible(true);
            }
        });
    }
}
