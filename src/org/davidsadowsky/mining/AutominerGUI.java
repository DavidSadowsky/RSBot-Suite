package org.davidsadowsky.mining;

import org.davidsadowsky.mining.data.Location;
import org.davidsadowsky.mining.data.Rock;
import org.davidsadowsky.mining.data.Time;
import org.rspeer.ui.Log;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AutominerGUI extends JFrame {

    private JComboBox rockComboBox;
    private JComboBox locationComboBox;
    private JComboBox logoutComboBox;
    private JButton initiate;

    public AutominerGUI() {
        super("Autominer Configuration");

        setLayout(new FlowLayout());
        initiate = new JButton("Initiate");
        locationComboBox = new JComboBox(Location.values());
        rockComboBox = new JComboBox(Rock.values());
        logoutComboBox = new JComboBox(Time.values());
        add(locationComboBox);
        add(logoutComboBox);
        add(rockComboBox);
        add(initiate);

        setDefaultCloseOperation(HIDE_ON_CLOSE);
        pack();

        locationComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Location location = (Location) locationComboBox.getSelectedItem();
                rockComboBox.setModel(new DefaultComboBoxModel(location.getRocks()));
            }
        });

        initiate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Autominer.location = (Location) locationComboBox.getSelectedItem();
                Autominer.rock = (Rock) rockComboBox.getSelectedItem();
                Autominer.time = (Time) logoutComboBox.getSelectedItem();
                Log.info("[Location: " + Autominer.location.getName() + ", Ore: " + Autominer.rock.getName() + ", Duration: " + Autominer.time.getTime() + "]" );
                setVisible(false);
            }
        });
    }
}