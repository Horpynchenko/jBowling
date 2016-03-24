package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class FXMLController {
    @FXML
    private Text actiontarget;

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {
        actiontarget.setText("Sign in button pressed");
    }

    public void setActiontarget(Text actiontarget) {
        this.actiontarget = actiontarget;
    }

    public Text getActiontarget() {
        return actiontarget;
    }

}
