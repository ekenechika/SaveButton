import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SaveAction extends AbstractAction {
    private final ArrayList<Item> selectedItems;
    private final String filePath;

    public SaveAction(String filePath) {
        // Set button text
        super("Save Selected Items");

        selectedItems = new ArrayList<>();
        this.filePath = filePath;
    }

    public void updateSelectedItems(List<Item> items) {
        selectedItems.clear();
        selectedItems.addAll(items);
    }

    /**
     * When the button is pressed, the selected items are saved to a CSV file.
     * The file path is given in the constructor.
     */
    public void actionPerformed(ActionEvent e) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Write CSV header
            writer.write("Item Name,Price\n");

            // Write selected items to CSV
            for (Item item : selectedItems) {
                writer.write(item.getName() + "," + item.getPrice() + "\n");
            }

            JOptionPane.showMessageDialog(null, "Selected items saved to " + filePath,
                    "Save Successful", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error saving items to " + filePath,
                    "Save Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}