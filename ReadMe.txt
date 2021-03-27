//Question1
How it works ?
* Senders
- Select a room to join. For now the only option available is the queue on which
you can send messages to a receiver.
- the text field is editable once you chose the room
- after you finish typing the text click on the room to send the message
* Receiver
- has a 2 uneditable textareas to receive messages

// Question2
functionality stays the same (Class SendProcess, recieve.ReceiveProcess)
adding a Document Listener to the fields where the users can type what they want to send
the button room when clicked for a second time transform the textarea to uneditable

// Question 3
after repackaging the Classes into to packages
I made only one Frame Sender that is instantiated 3 times by the mainSending class
after asking for a queue name

for the reception
I made the receiving class gets the list of queues existing from the RabbitMQ api
than for each one it creates a Zone where it displays it's message sent

// Question 4 & 5
I changed the sender frame to be having on he 4th question  2 field
one for it to write and an other one to see the other sender text
than for the 5th question a multi uneditable text areas being constructed the same way as for the question  3
a problem occurs : when Sender 1 is logged in and if sender2 joins after him
sender2's zone doesn't appear in for sender1
to solve this issue I created a process that sender1 takes to refresh his frame:q