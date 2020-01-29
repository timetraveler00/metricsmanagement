import java.awt.BorderLayout;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

public class SwingScrollBar extends JPanel {

  JLabel label;

  public SwingScrollBar() {
    super(true);
    label = new JLabel();
    setLayout(new BorderLayout());

    JScrollBar hbar = new JScrollBar(JScrollBar.HORIZONTAL, 30, 20, 0, 300);
    JScrollBar vbar = new JScrollBar(JScrollBar.VERTICAL, 30, 40, 0, 300);

    hbar.setUnitIncrement(2);
    hbar.setBlockIncrement(1);

    hbar.addAdjustmentListener(new MyAdjustmentListener());
    vbar.addAdjustmentListener(new MyAdjustmentListener());

    add(hbar, BorderLayout.SOUTH);
    add(vbar, BorderLayout.EAST);
    add(label, BorderLayout.CENTER);
  }

  class MyAdjustmentListener implements AdjustmentListener {
    public void adjustmentValueChanged(AdjustmentEvent e) {
      label.setText("    New Value is " + e.getValue() + "      ");
      repaint();
    }


  }
}