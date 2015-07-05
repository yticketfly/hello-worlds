package ervii.farm.poems.blender

import scala.swing._
import scala.swing.event._
object FacePalm {//extends SimpleSwingApplication {
  def top = new MainFrame {
    title = "Poem Garden"
    val button = new Button {
      text = "Click me"
    }
    val label = new Label {
      text = "Click to Start"
    }
    contents = new BoxPanel(Orientation.Vertical) {
      contents += button
      contents += label
      border = Swing.EmptyBorder(60, 60, 10, 60)
    }
    listenTo(button)
    reactions += {
      case ButtonClicked(b) =>
        label.text = CardDealer.nextLine()
    } }
}