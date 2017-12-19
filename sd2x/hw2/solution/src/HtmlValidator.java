import java.util.Iterator;
import java.util.Queue;
import java.util.Stack;

/*
 * SD2x Homework #2
 * Implement the method below according to the specification in the assignment description.
 * Please be sure not to change the method signature!
 */

public class HtmlValidator {
	
	public static Stack<HtmlTag> isValidHtml(Queue<HtmlTag> tags) {
		Stack<HtmlTag> validTags = new Stack<HtmlTag>();
		
		Iterator<HtmlTag> itr = tags.iterator();
		while(itr.hasNext()) {
			HtmlTag tag = itr.next();
			if (tag.isOpenTag())
				validTags.push(tag);
			else if (tag.isSelfClosing()) {
				// ignore this one
			}
			else if (validTags.isEmpty()) {
				validTags = null;
				break;
			}
			else if (tag.matches(validTags.peek())) {
				validTags.pop();
			}
			else {
				break;
			}
		}

		return validTags;
	}
	

}

