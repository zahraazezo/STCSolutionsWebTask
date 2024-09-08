package Utils;

public enum WaitConditions {
    /// <summary>
    /// Wait till given element exists in html
    /// </summary>
    Exist,
    /// <summary>
    /// Wait till given element doesn't exist in html
    /// </summary>
    NotExist,
    /// <summary>
    /// Wait till given element is visible
    /// </summary>
    Visible,
    /// <summary>
    /// Wait till given element is invisible
    /// </summary>
    Invisible,
    /// <summary>
    /// Wait till given element is enabled
    /// </summary>
    Enabled,
    /// <summary>
    /// Wait till given element is clickable
    /// </summary>
    Clickable,
    /// <summary>
    /// Wait till frame is available and switch to it
    /// </summary>
    FrameAvailabilityAndSwitchToIt,
    /// <summary>
    /// Wait till select list of given element contains more than 1 option
    /// </summary>
    SelectListLoaded,
    /// <summary>
    /// Wait till page ready state is interactive or complete
    /// </summary>
    PageReadyState,
    /// <summary>
    /// Wait till all elements with given locator exist in html
    /// </summary>
    PresenceOfAllElementsLocatedBy,
    /// <summary>
    /// Wait till all elements with given locator are visible
    /// </summary>
    VisibilityOfAllElementsLocatedBy,
    /// <summary>
    /// Wait till given element is selected
    /// </summary>
    ElementToBeSelected,
    /// <summary>
    /// Wait till an alert is present
    /// </summary>
    AlertIsPresent
}
