Project Decription:

We will be building an “MS Paint”-like application in Java called JPaint. It will
have the following features:
- Pick a shape
o Ellipse
o Triangle
o Rectangle
- Pick a primary color
- Pick a secondary color
- Select shading type (outline only, filled-in, outline and filled-in)
- Click and drag to draw a shape
- Click and drag to select shapes
- Click and drag to move selected shapes
- Copy selected shapes
- Paste copied shapes
- Delete selected shapes
- Undo last action
- Redo last action
- Group selected shapes
- Ungroup selected shapes
- Selected shapes have dashed outline
I am providing the GUI. You should need to write minimal GUI code. You will
need to call into the Java API for drawing shapes. You will also need to write
some handlers for click and drag events.
Features by Sprint
Sprint 1 (Ends on Week 4)
- Draw a filled-in Rectangle
o Click and drag while in Draw mode – a Rectangle will display on
the screen on mouse release. The Rectangle will match the
direction and size of the mouse movement. Rectangle does not
need to display while clicking and dragging – it will suddenly
appear on the screen only once the mouse is released.
- Undo/Redo Draw
- Have at least one design pattern implemented
Grading Notes:
- For grading purposes, the order in which shapes appear on canvas
don’t matter. If you draw one shape on top of another, undo, then redo,
and the order changes, that’s okay!
Sprint 2 (Ends on Week 6)
- Draw Rectangles, Ellipses, and Triangles
- Draw shapes with various colors
- Draw shapes with various shading types
o Outline Only – Only shape outline will be drawn. Use Primary
Color to draw this.
o Filled-In – Only the inside of the shape will be drawn – there will
be no visible outline. Use Primary Color to draw this.
o Outline and Filled-In – Both the inside and the outline will be
drawn. Use Primary Color for the inside and Secondary Color for
the outline.
- Select a shape.
o In Select mouse mode, select any shapes that are touched by the
invisible bounding box created by clicking and dragging to select.
You can use (and share on D2L) a Collision detection algorithm
that you find. The selection can be imprecise; when selecting,
assume any shape (e.g. ellipse or triangle) has an invisible
bounding box that surrounds the shape. You can use that
bounding box for your collision detection calculation (this is much
easier for you!).
o If you click a single point on a shape while in Select mode, that
shape should be selected. If you click a single point on the canvas 
or select an empty area, the selected shapes should be
deselected This is the default behavior for collision detection and
shouldn’t require any modification – this is easier for you!
o You should be able to click and drag into any part of a shape to
select it – it does not need to be completely surrounded
o At this point, nothing visible has to happen.
- Move a shape
o In Move Mouse Mode, clicking and dragging will offset any
Selected shapes by the amount your mouse moves.
o Moving should not deselect any shapes
- Undo/Redo Move
- Have at least two design patterns implemented
Grading Notes:
- The ability to move a shape is dependent on the ability to select a
shape.
- Shape selection must include the ability to click and drag to select
multiple shapes at once. You should not be able to click on shapes one
at a time to select
- You can move by clicking and dragging anywhere on the screen, you
don’t need to click and drag on the highlighted shape(s).
Sprint 3 (Ends on Week 8)
- Copy
o Adds selected shapes to the “clipboard”. Nothing visible occurs on
the screen.
o Copying should not deselect shapes
- Paste
o If there is anything on the clipboard, paste it on the screen
somewhere. You can paste it at origin (0, 0), some offset of the
original shapes, or somewhere else that makes sense. Do not
paste to the same location as the original shapes; you will not be
able to see the pasted shapes and it will get marked as “not
working”.
- Delete
o Deletes the selected shape(s)
- Outline Selected Shapes
o Shapes that are selected will display with a dashed outline around
them. These will need to be offset slightly so they don’t overlap
the shape. Move the start point up and to the left 5px and add
10px to the height and width. You can adjust these values
depending on personal preference.
o The outline must be the same shape as the shape that’s selected
- Undo/Redo Paste and Delete
- Have at least three design patterns implemented
Sprint 4 (Ends on Week 10)
- Group
o Clicking this button will cause all Selected shapes to operate as a
group.
o Shapes grouped together should be operated on as if they were
one shape.
o To select a grouped shape, any part of the invisible bounding box
around the shapes in the group can be selected.
o The selection box should display along the boundaries of the
group of shapes, NOT the individual shapes
o Groups can be part of other groups
- Ungroup
o Any selected shapes that are grouped shapes will no longer be
grouped.
o If a selected group is comprised of one or more groups, only the
outer-most group is ungrouped
- Undo/Redo Group and Ungroup
- Have at least five design patterns implemented
Non-functional requirements
- Must use at least 5 design patterns. Two may be the same kind of
pattern. Please note you won’t get credit for using MVC as a design
pattern. Further, you won’t get credit for using the Java SDK
implementations of patterns (e.g. Observer/Iterator).
- Must use Source Control (see details below).
- Report (see below for details).
- The application must be written in Java using the Java SDK 1.14 or
higher.
- Only features and capabilities that are part of the Java2 SDK may be
used in the application. No third-party software such as BlueJay or
JBuilder class libraries or COM/CORBA components.
