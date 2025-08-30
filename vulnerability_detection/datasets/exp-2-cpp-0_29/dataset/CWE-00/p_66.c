bool Buffer::operator==(const Buffer &other) const {
  return this->data == other.data && this->extensions == other.extensions &&
         this->extras == other.extras && this->name == other.name &&
         this->uri == other.uri;
}