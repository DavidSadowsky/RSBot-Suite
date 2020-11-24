package org.dsadowsky.mining;

import org.dsadowsky.mining.data.Location;
import org.dsadowsky.mining.data.Rock;

import javax.swing.*;
import java.awt.*;

public class AutominerGUI extends JFrame {

    private JComboBox rockComboBox;
    private JComboBox locationComboBox;
    private JButton initiate;

    public AutominerGUI() {
        super("Autominer Configuration");

        setLayout(new FlowLayout());
        initiate = new JButton("Initiate");
        locationComboBox = new JComboBox(Location.values());
        rockComboBox = new JComboBox(Rock.values());
        add(locationComboBox);
        add(initiate);
        add(rockComboBox);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        pack();
    }

    public static void main(String... args) {
        new AutominerGUI().setVisible(true);
    }
}
