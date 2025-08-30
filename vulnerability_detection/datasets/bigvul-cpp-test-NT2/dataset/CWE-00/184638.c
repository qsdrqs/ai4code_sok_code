   void non_vulnerable_func() {
    context_.network_message_loop()->PostTask(
        FROM_HERE, base::Bind(
            &ClientSession::OnConnectionClosed, client_, connection_));
   }
