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
functionality stays the same (Class send.SendProcess, recieve.ReceiveProcess)
adding a Document Listener to the fields where the users can type what they want to send
the button room when clicked for a second time transform the textarea to uneditable

// Question 3
after repackaging the Classes into to packages
I made only one Frame Sender that is instantiated 3 times by the mainSending class
after asking for a queue name

for the reception
I made the receiving class gets the list of queues existing from the RabbitMQ api
than for each one it creates a Zone where it displays it's message sent