package org.davidsadowsky.firemaking;

import org.davidsadowsky.firemaking.data.Location;
import org.davidsadowsky.firemaking.data.Logs;
import org.davidsadowsky.firemaking.data.Time;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AutofiremakerGUI extends JFrame {

    private JComboBox logComboBox;
    private JComboBox locationComboBox;
    private JComboBox logoutComboBox;
    private JButton initiate;

    public AutofiremakerGUI() {
        super("Autofiremaker Configuration");

        setLayout(new FlowLayout());
        initiate = new JButton("Initiate");
        locationComboBox = new JComboBox(Location.values());
        logComboBox = new JComboBox(Logs.values());
        logoutComboBox = new JComboBox(Time.values());
        add(locationComboBox);
        add(logoutComboBox);
        add(logComboBox);
        add(initiate);

        setDefaultCloseOperation(HIDE_ON_CLOSE);
        pack();

        locationComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Location location = (Location) locationComboBox.getSelectedItem();
                logComboBox.setModel(new DefaultComboBoxModel(location.getLogs()));
            }
        });

        initiate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Autofiremaker.location = (Location) locationComboBox.getSelectedItem();
                Autofiremaker.logs = (Logs) logComboBox.getSelectedItem();
                Autofiremaker.time = (Time) logoutComboBox.getSelectedItem();
                org.rspeer.ui.Log.info("[Location: " + Autofiremaker.location.getName() + ", Logs: " + Autofiremaker.logs.getName() + ", Duration: " + Autofiremaker.time.getTime() + "]" );
                setVisible(false);
            }
        });
    }
}