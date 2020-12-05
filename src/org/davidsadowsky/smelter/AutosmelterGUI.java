package org.davidsadowsky.smelter;

import org.davidsadowsky.smelter.data.Location;
import org.davidsadowsky.smelter.data.Bar;
import org.davidsadowsky.smelter.data.Time;
import org.rspeer.ui.Log;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AutosmelterGUI extends JFrame {

    private JComboBox barComboBox;
    private JComboBox locationComboBox;
    private JComboBox timeComboBox;
    private JButton initiate;

    public AutosmelterGUI() {
        super("Autosmelter Configuration");

        setLayout(new FlowLayout());
        initiate = new JButton("Initiate");
        locationComboBox = new JComboBox(Location.values());
        barComboBox = new JComboBox(Bar.values());
        timeComboBox = new JComboBox(Time.values());
        add(locationComboBox);
        add(timeComboBox);
        add(barComboBox);
        add(initiate);

        setDefaultCloseOperation(HIDE_ON_CLOSE);
        pack();

        locationComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Location location = (Location) locationComboBox.getSelectedItem();
                barComboBox.setModel(new DefaultComboBoxModel(location.getBars()));
            }
        });

        initiate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Autosmelter.location = (Location) locationComboBox.getSelectedItem();
                Autosmelter.bar = (Bar) barComboBox.getSelectedItem();
                Autosmelter.time = (Time) timeComboBox.getSelectedItem();
                Log.info("[Location: " + Autosmelter.location.getName() + ", Bar: " + Autosmelter.bar.getName() + ", Duration: " + Autosmelter.time.getTime() + "]" );
                setVisible(false);
            }
        });
    }
}