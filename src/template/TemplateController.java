package template;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TemplateController implements Initializable{

	@FXML
	private RadioButton temp1;
	@FXML
	private RadioButton temp2;
	@FXML
	private RadioButton temp3;
	@FXML
	private ImageView tempimg;
	@FXML
	ToggleGroup temprb;

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			
			final Image tempImage = new Image(getClass().getResourceAsStream("/images/T1.jpg"));
			tempimg.setImage(tempImage); 
			
			temprb.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
			    public void changed(ObservableValue<? extends Toggle> ov,
			        Toggle old_toggle, Toggle new_toggle) {
			            if (temprb.getSelectedToggle() != null) {
			            	RadioButton chk = (RadioButton)old_toggle.getToggleGroup().getSelectedToggle();
			            	System.out.println("Selected Radio Button - "+chk.getText());
			                final Image image = new Image(
			                    getClass().getResourceAsStream("/images/"+
			                        chk.getText().toString() + 
			                           ".jpg"
			                     // Cast object to radio button
			                   // System.out.println("Selected Radio Button - "+chk.getText());
			                        
			                    )
			                );
			                tempimg.setImage(image);
			            }                
			        }
			});
		}
	
	}
