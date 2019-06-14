package uqac.distributedsystems.model;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * The type Room.
 */
public class Room extends JComponent {
    private String label;
    private Coordinate coords;
    private int width;
    private int height;
    private Color background;
    private ArrayList<Device> devices;

    /**
     * Instantiates a new Room.
     *
     * @param label      the label
     * @param coords     the coords
     * @param width      the width
     * @param height     the height
     * @param background the background
     */
    public Room(String label, Coordinate coords, int width, int height, Color background) {
        this.label = label;
        this.coords = coords;
        this.width = width;
        this.height = height;
        this.background = background;
        this.devices = new ArrayList<Device>();
    }

    /**
     * Add device.
     *
     * @param device the device
     */
    public void addDevice(Device device) {
        devices.add(device);
    }

    /**
     * Remove device.
     *
     * @param device the device
     */
    public void RemoveDevice(Device device) {
        devices.remove(device);
    }

    /**
     * Gets label.
     *
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * Sets label.
     *
     * @param label the label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * Gets coords.
     *
     * @return the coords
     */
    public Coordinate getCoords() {
        return coords;
    }

    /**
     * Sets coords.
     *
     * @param coords the coords
     */
    public void setCoords(Coordinate coords) {
        this.coords = coords;
    }

    public int getWidth() {
        return width;
    }

    /**
     * Sets width.
     *
     * @param width the width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    /**
     * Sets height.
     *
     * @param height the height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    public Color getBackground() {
        return background;
    }

    public void setBackground(Color background) {
        this.background = background;
    }

    /**
     * Gets device.
     *
     * @return the device
     */
    public ArrayList<Device> getDevices() {
        return devices;
    }

    /**
     * Sets device.
     *
     * @param devices the devices
     */
    public void setDevices(ArrayList<Device> devices) {
        this.devices = devices;
    }

    /**
     * paint Component
     * @param g Graphics
     */
    public void paintComponent(Graphics g) {
        g.setColor(this.getBackground());
        g.fillRect(this.getCoords().getX(), this.getCoords().getY(), this.getWidth(), this.getHeight());
    }
}
